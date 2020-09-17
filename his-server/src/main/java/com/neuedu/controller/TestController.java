package com.neuedu.controller;

import com.neuedu.pojo.UmsUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/25  10:31 25
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */

@RestController
public class TestController {


    @GetMapping("testuser")
    UmsUser test(UmsUser user){
        return user;
    }



}
