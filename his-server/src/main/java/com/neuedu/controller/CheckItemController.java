package com.neuedu.controller;


import com.neuedu.pojo.CheckItem;
import com.neuedu.service.ICheckItemService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 检查项目 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/check-item")
public class CheckItemController {

    @Resource
    ICheckItemService checkItemService;

    @GetMapping("/list")
    CommonResult list(CheckItem checkItem) {
        return CommonResult.success(checkItemService.list(checkItem));
    }


    @PostMapping("/add")
    CommonResult add(CheckItem checkItem) {
        if(checkItemService.add(checkItem))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("项目名称已经存在");
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(checkItemService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(CheckItem checkItem) {
        return CommonResult.success(checkItemService.updateById(checkItem));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param checkItem
     * @return
     */
    @PostMapping("/del")
    CommonResult del(CheckItem checkItem) {
        checkItem.setActive(0);
        return CommonResult.success(checkItemService.updateById(checkItem));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(checkItemService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(CheckItem checkItem) {
        checkItem.setActive(1);
        return CommonResult.success(checkItemService.updateById(checkItem));
    }
}
