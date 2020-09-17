package com.neuedu.controller;


import com.neuedu.pojo.UmsUserRole;
import com.neuedu.service.IUmsUserRoleService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户与角色关系表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/ums-user-role")
public class UmsUserRoleController {
    @Resource
    IUmsUserRoleService umsUserRoleService;
    @PostMapping("/save")
    CommonResult save(UmsUserRole umsUserRole) {
        return CommonResult.success(umsUserRoleService.saveOrUpdate(umsUserRole));
    }
    @GetMapping("/getByUserId")
    CommonResult getByUserId(Integer userId) {
        return CommonResult.success(umsUserRoleService.getByUserId(userId));
    }
}
