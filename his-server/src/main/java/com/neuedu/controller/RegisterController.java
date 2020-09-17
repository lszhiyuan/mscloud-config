package com.neuedu.controller;


import com.neuedu.pojo.Register;
import com.neuedu.service.IRegisterService;
import com.neuedu.service.RegisterServiceProxy;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 诊疗信息 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    IRegisterService registerService = new RegisterServiceProxy();


    @PostMapping("/regist")
    CommonResult regist(Register register){
        return CommonResult.success(registerService.add(register));
    }


    @GetMapping("/list")
    CommonResult list(Register register) {
        return CommonResult.success(registerService.list(register));
    }


    @GetMapping("/maxNum")
    CommonResult maxNum() {
        return CommonResult.success(registerService.maxNum());
    }

    @GetMapping("/showFee")
    CommonResult showFee(Integer registerId){
        return CommonResult.success(registerService.showFee(registerId));
    }

    /**
     * 收费的功能
     */
    @PostMapping("/collectFee")
    CommonResult collectFee(Integer[] checkApplyIds,  Integer[] inspectApplyIds){
        return CommonResult.success(registerService.collectFee(checkApplyIds,inspectApplyIds));
    }


    /**
     * 收费的功能
     */
    @PostMapping("/refund")
    CommonResult refund(Integer[] checkApplyIds,  Integer[] inspectApplyIds){
        return CommonResult.success(registerService.refund(checkApplyIds,inspectApplyIds));
    }


    @PostMapping("/update")
    CommonResult update(Register register){
        return CommonResult.success(registerService.updateById(register));
    }

}
