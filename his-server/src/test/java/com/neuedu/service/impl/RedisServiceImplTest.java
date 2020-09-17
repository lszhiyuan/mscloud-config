package com.neuedu.service.impl;

import com.neuedu.App;
import com.neuedu.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/27  14:38 27
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisServiceImplTest {

    @Resource
    IRedisService redisService;


    @Test
    public void set() {

        redisService.set("user","abcde",50);
    }

    @Test
    public void get() {
        redisService.set("user","abcde",50);
        boolean has = redisService.hashkey("user");
        System.out.println(has);
        if(has){
            String user = redisService.get("user");
            System.out.println("get:--->  "+user);
        }


    }

    @Test
    public void hashkey() {
    }

    @Test
    public void expire()  {
        redisService.set("user","abcde",50);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.expire("user",200);
    }
}