package com.neuedu.controller;


import com.neuedu.pojo.ConstantItem;
import com.neuedu.service.IConstantItemService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 常数项表 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/constant-item")
public class ConstantItemController {
    @Resource
    IConstantItemService constantItemService;

    @GetMapping("/list")
    CommonResult list(ConstantItem constantItem) {
        return CommonResult.success(constantItemService.list(constantItem));
    }


    @PostMapping("/add")
    CommonResult add(ConstantItem constantItem) {
        if(constantItemService.add(constantItem))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("常数项代码已经存在");
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(constantItemService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(ConstantItem constantItem) {
        return CommonResult.success(constantItemService.updateById(constantItem));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param constantItem
     * @return
     */
    @PostMapping("/del")
    CommonResult del(ConstantItem constantItem) {
        constantItem.setActive(0);
        return CommonResult.success(constantItemService.updateById(constantItem));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(constantItemService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(ConstantItem constantItem) {
        constantItem.setActive(1);
        return CommonResult.success(constantItemService.updateById(constantItem));
    }
}
