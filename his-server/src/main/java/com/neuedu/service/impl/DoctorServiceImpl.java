package com.neuedu.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.mapper.RegisterMapper;
import com.neuedu.pojo.Register;
import com.neuedu.service.IDoctorService;
import com.neuedu.service.IRedisService;
import com.neuedu.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/28  10:21 28
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class DoctorServiceImpl implements IDoctorService {

    @Resource
    IRedisService redisService;

    @Resource
    RegisterMapper registerMapper;

    @Override
    public List<Register> registList(String type,String token) throws IOException {
        QueryWrapper<Register> wrapper = new QueryWrapper<>();
        DecodedJWT decode = JWT.decode(token);
        String username = decode.getClaim("username").asString();
        String json = redisService.get(JWTUtil.KEY +"-"+username);

        ObjectMapper objectMapper = new ObjectMapper();
        Map userInfo = objectMapper.readValue(json, Map.class);

        Integer userId = decode.getClaim("id").asInt();
        Integer deptId = decode.getClaim("deptId").asInt();

//        System.out.println("userId--> "+userId);
//        System.out.println("deptId--> "+deptId);

        //构造查询条件
        if("0".equals(type)){
            wrapper.eq("doctor_id",userId);
        }else{
            wrapper.eq("dept_id",deptId);
        }
        wrapper.eq("visittime", LocalDate.now());

        return registerMapper.selectList(wrapper);
    }


    public static void main(String[] args) {
        Set<String> set = new HashSet();
        set.add(new String("1"));
        set.add(new String("1"));
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        Object[] array =  set.toArray();
        String[] ids = new String[set.size()];
        set.toArray(ids);
//        for (Object o : array) {
//            System.out.println(o.getClass()+"\t"+o);
//        }
        for (String id : ids) {
            System.out.println(id);
        }
//        set.forEach( System.out::println);
    }




}