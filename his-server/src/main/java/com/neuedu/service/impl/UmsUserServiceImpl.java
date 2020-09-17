package com.neuedu.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.mapper.UmsUserMapper;
import com.neuedu.pojo.UmsUser;
import com.neuedu.service.IRedisService;
import com.neuedu.service.IUmsUserService;
import com.neuedu.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    IRedisService redisService;


    @Override
    public String token(UmsUser user) throws JsonProcessingException {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        //根据用户名查询 用户对象
        wrapper.eq("username",user.getUsername());
        UmsUser queryUser = this.getOne(wrapper);
        if(queryUser!=null){
            //如果用户名存在则验证密码
            //如果密码一致则认为登录成功
            if(bCryptPasswordEncoder.matches(user.getPassword(),queryUser.getPassword())){
                //将用户信息生成token数据返回给前端

                LocalDateTime now = LocalDateTime.now();
                queryUser.setLastlogin(now);
                String token =JWTUtil.create(queryUser);

                //保存登录状态信息 --》 ssdb
                //umsuser-admin

                updateLastLogin(queryUser.getId(),now);

                //缓存用户登录信息
                write2SSDB(queryUser,now);

                return token;
            }
        }

        return null;
    }

    private void write2SSDB(UmsUser user,LocalDateTime now) throws JsonProcessingException {
        Map info = new HashMap<>();
        info.put("username",user.getUsername());
        info.put("password",user.getPassword());
        info.put("id",user.getId());
        info.put("deptId",user.getDeptId());
        info.put("lastlogin",now.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        ObjectMapper objectMapper = new ObjectMapper();
        String key = JWTUtil.KEY +"-"+user.getUsername();
        String userJson = objectMapper.writeValueAsString(info);

        redisService.set(key,userJson,60 *30);
    }

    /**
     * 更新最后登录成功的时间
     * @param id
     * @param now
     */
    private void updateLastLogin(Integer id, LocalDateTime now) {
        UmsUser user = new UmsUser();
        user.setId(id);
        user.setLastlogin(now);
        updateById(user);
    }

    @Override
    public Object list(UmsUser umsUser) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(umsUser.getUsername())) {
            wrapper.like("username",umsUser.getUsername());
        }
        if(umsUser.getRegistlevel() !=null) {
            wrapper.eq("registlevel",umsUser.getRegistlevel());
        }
        if(umsUser.getDeptId() !=null) {
            wrapper.eq("dept_id",umsUser.getDeptId());
        }
        if(umsUser.getActive() !=null) {
            wrapper.eq("active",umsUser.getActive());
        }

        // 如果分页返回 IPage 如果不分页 返回 List
        if(umsUser.getWithPage() == 1) {
            return this.page(new Page<>(umsUser.getPageNo(),umsUser.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(UmsUser umsUser) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",umsUser.getUsername());
        UmsUser query = this.getOne(wrapper);
        if(query == null )
            return this.save(umsUser);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        UmsUser user = new UmsUser();
        user.setActive(0);
        UpdateWrapper<UmsUser> wrapper = new UpdateWrapper<>();
        wrapper.in("id",list);
        return this.update(user,wrapper);
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JWT.decode(token).getClaim("username").asString();
        //删除key
        String key = JWTUtil.KEY +"-"+username;

        //设置有效期为0
        redisService.expire(key,0);

        return true;
    }

    public static void main(String[] args) {
        int num = 100_1_0;
        System.out.println(num);
    }
}
