package com.neuedu.controller;


import com.neuedu.pojo.ConstantType;
import com.neuedu.service.IConstantTypeService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 常数类别 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/constant-type")
public class ConstantTypeController {

    @Resource
    IConstantTypeService constantTypeService;

    @GetMapping("/list")
    CommonResult list(ConstantType constantType) {
        return CommonResult.success(constantTypeService.list(constantType));
    }


    @PostMapping("/add")
    CommonResult add(ConstantType constantType) {
        if(constantTypeService.add(constantType))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("类别码已经存在");
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(constantTypeService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(ConstantType constantType) {
        return CommonResult.success(constantTypeService.updateById(constantType));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param constantType
     * @return
     */
    @PostMapping("/del")
    CommonResult del(ConstantType constantType) {
        constantType.setActive(0);
        return CommonResult.success(constantTypeService.updateById(constantType));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(constantTypeService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(ConstantType constantType) {
        constantType.setActive(1);
        return CommonResult.success(constantTypeService.updateById(constantType));
    }
}
