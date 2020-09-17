package com.neuedu.controller;


import com.neuedu.pojo.UmsRole;
import com.neuedu.pojo.UmsUser;
import com.neuedu.service.IUmsRoleService;
import com.neuedu.service.IUmsUserService;
import com.neuedu.util.CommonResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/ums-role")
public class UmsRoleController {

    @Resource
    IUmsRoleService umsRoleService;
    @GetMapping("/list")
    CommonResult list(UmsRole umsRole) {
        return CommonResult.success(umsRoleService.list(umsRole));
    }
    @PostMapping("/add")
    CommonResult add(UmsRole umsRole) {
        if(umsRoleService.add(umsRole))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("角色名已经存在");
    }
    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(umsRoleService.getById(id));
    }
    @PostMapping("/update")
    CommonResult update(UmsRole umsRole) {
        return CommonResult.success(umsRoleService.updateById(umsRole));
    }
    @PostMapping("/del")
    CommonResult del(UmsRole umsRole) {
        umsRole.setActive(0);
        return CommonResult.success(umsRoleService.updateById(umsRole));
    }
    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(umsRoleService.batchdel(ids));
    }
    @PostMapping("/back")
    CommonResult back(UmsRole umsRole) {
        umsRole.setActive(1);
        return CommonResult.success(umsRoleService.updateById(umsRole));
    }
}
