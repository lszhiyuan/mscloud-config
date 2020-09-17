package com.neuedu.controller;


import com.neuedu.pojo.InspectItem;
import com.neuedu.service.IInspectItemService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 检验项目 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/inspect-item")
public class InspectItemController {

    @Resource
    IInspectItemService inspectItemService;

    @GetMapping("/list")
    CommonResult list(InspectItem inspectItem) {
        return CommonResult.success(inspectItemService.list(inspectItem));
    }


    @PostMapping("/add")
    CommonResult add(InspectItem inspectItem) {
        if(inspectItemService.add(inspectItem))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("项目名称已经存在");
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(inspectItemService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(InspectItem inspectItem) {
        return CommonResult.success(inspectItemService.updateById(inspectItem));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param inspectItem
     * @return
     */
    @PostMapping("/del")
    CommonResult del(InspectItem inspectItem) {
        inspectItem.setActive(0);
        return CommonResult.success(inspectItemService.updateById(inspectItem));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(inspectItemService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(InspectItem inspectItem) {
        inspectItem.setActive(1);
        return CommonResult.success(inspectItemService.updateById(inspectItem));
    }
}
