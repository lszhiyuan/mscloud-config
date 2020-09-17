package com.neuedu.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.App;
import com.neuedu.pojo.UmsUser;
import com.neuedu.service.IUmsUserService;
import com.neuedu.util.JWTUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/28  10:42 28
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class JWTTest {

    @Resource
    IUmsUserService umsUserService;

    @Test
    public void testJWT(){
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        //根据用户名查询 用户对象
        wrapper.eq("username","user1");
        UmsUser queryUser = umsUserService.getOne(wrapper);

        String token = JWTUtil.create(queryUser);


        DecodedJWT decode = JWT.decode(token);

        String username = decode.getClaim("username").asString();
        String password = decode.getClaim("password").asString();
        String id = decode.getClaim("id").asString();
        String deptId = decode.getClaim("deptId").asString();

        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("id:"+id);
        System.out.println("deptId:"+deptId);

    }



}
