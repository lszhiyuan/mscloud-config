package com.neuedu.controller;


import com.neuedu.pojo.RegistLevel;
import com.neuedu.service.IRegistLevelService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 挂号级别 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/regist-level")
public class RegistLevelController {

    @Resource
    IRegistLevelService registLevelService;

    @GetMapping("/list")
    CommonResult list(RegistLevel registLevel) {
        return CommonResult.success(registLevelService.list(registLevel));
    }


    @PostMapping("/add")
    CommonResult add(RegistLevel registLevel) {
      return CommonResult.success(registLevelService.save(registLevel));
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(registLevelService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(RegistLevel registLevel) {
        return CommonResult.success(registLevelService.updateById(registLevel));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param registLevel
     * @return
     */
    @PostMapping("/del")
    CommonResult del(RegistLevel registLevel) {
        registLevel.setActive(0);
        return CommonResult.success(registLevelService.updateById(registLevel));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(registLevelService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(RegistLevel registLevel) {
        registLevel.setActive(1);
        return CommonResult.success(registLevelService.updateById(registLevel));
    }
}
