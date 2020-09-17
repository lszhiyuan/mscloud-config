package com.neuedu.controller;

import com.neuedu.pojo.Register;
import com.neuedu.service.IDoctorService;
import com.neuedu.service.IRegisterService;
import com.neuedu.util.CommonResult;
import com.neuedu.util.HisConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/8/28  10:15 28
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    IDoctorService doctorService;

    @Resource
    IRegisterService registerService;

    // http://127.0.0.1:8081/doctor/regist-list
    @GetMapping("/regist-list")
    CommonResult registList(String type, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        return CommonResult.success(doctorService.registList(type,token)) ;
    }

    @PostMapping("/regist-submit")
    CommonResult registSubmit(Register register) throws IOException {
        register.setStatus(HisConstants.REGISTER_DEAL);
        return CommonResult.success(registerService.updateById(register));
    }



}
