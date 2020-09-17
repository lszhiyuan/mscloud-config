package com.neuedu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.App;
import com.neuedu.pojo.UmsPermission;
import com.neuedu.service.IUmsPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/9/2  15:32 02
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class UmsPermissionServiceImplTest {

    @Resource
    IUmsPermissionService umsPermissionService;


    @Test
    public void userPermissionList() throws JsonProcessingException {
        Integer userId = 5;
        List<UmsPermission> userPermissionList = umsPermissionService.userPermissionList(userId);

        userPermissionList.forEach(System.out::println);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userPermissionList);
        System.out.println("json:\r\n\r\n\r\n"+json);
    }
}