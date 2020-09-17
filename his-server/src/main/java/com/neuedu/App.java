package com.neuedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/25  10:27 25
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootApplication
@MapperScan("com.neuedu.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

}
