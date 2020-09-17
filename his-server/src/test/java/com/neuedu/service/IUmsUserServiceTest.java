package com.neuedu.service;

import com.neuedu.App;
import com.neuedu.pojo.UmsUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/25  10:37 25
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class IUmsUserServiceTest {

    @Resource
    IUmsUserService umsUserService;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void add() {
        for (int i = 2; i < 12 ; i++) {
            UmsUser user = new UmsUser("user"+i,
                    bCryptPasswordEncoder.encode("123456"),
                    "管理员",
                    "13888888888",
                    1,
                    null,
                    null,
                    null,
                    1,
                    null
            );
            umsUserService.add(user);
        }


    }
}