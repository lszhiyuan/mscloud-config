package com.neuedu.controller;


import com.neuedu.service.ICheckApplyService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 检查申请 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/check-apply")
public class CheckApplyController {

    @Resource
    ICheckApplyService checkApplyService;

    @PostMapping("/save")
    CommonResult save(Integer registerId,Integer[] itemId){
        return CommonResult.success(checkApplyService.save(registerId,itemId));
    }

    @PostMapping("/list")
    CommonResult list(Integer registerId){
        return CommonResult.success(checkApplyService.list(registerId));
    }


    public static  void  main(String args[]) throws InterruptedException {
        while(true){
            System.out.println("测试网络--屏幕在动"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(1000);
        }
    }

}
