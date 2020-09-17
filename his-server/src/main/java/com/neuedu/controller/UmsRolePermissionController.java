package com.neuedu.controller;


import com.neuedu.pojo.UmsRolePermission;
import com.neuedu.service.IUmsRolePermissionService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色与权限关系表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/ums-role-permission")
public class UmsRolePermissionController {
    @Resource
    IUmsRolePermissionService umsRolePermissionService;
    @GetMapping("/get-permiss")
    CommonResult getPermiss() {
        return CommonResult.success(umsRolePermissionService.getPermission());
    }
    @PostMapping("/save")
    CommonResult save(UmsRolePermission umsRolePermission) {
        return CommonResult.success(umsRolePermissionService.saveOrUpdate(umsRolePermission));
    }
    @GetMapping("/getByRoleId")
    CommonResult getByRoleId(Integer roleId) {
        return CommonResult.success(umsRolePermissionService.getByRoleId(roleId));
    }
}
