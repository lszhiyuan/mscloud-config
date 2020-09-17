package com.neuedu.controller;


import com.neuedu.service.IInspectApplyService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 检验申请 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/inspect-apply")
public class InspectApplyController {

    @Resource
    IInspectApplyService inspectApplyService;

    @PostMapping("/save")
    CommonResult save(Integer registerId, Integer[] itemId){
        return CommonResult.success(inspectApplyService.save(registerId,itemId));
    }


    @PostMapping("/list")
    CommonResult list(Integer registerId){
        return CommonResult.success(inspectApplyService.list(registerId));
    }


}
