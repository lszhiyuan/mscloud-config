package com.neuedu.controller;


import com.auth0.jwt.JWT;
import com.neuedu.pojo.UmsPermission;
import com.neuedu.service.IUmsPermissionService;
import com.neuedu.util.CommonResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/ums-permission")
public class UmsPermissionController {
    @Resource
    BCryptPasswordEncoder encoder;
    @Resource
    IUmsPermissionService umsPermissionService;
    @GetMapping("/list")
    CommonResult list(UmsPermission umsPermission) {
        return CommonResult.success(umsPermissionService.list(umsPermission));
    }
    @PostMapping("/add")
    CommonResult add(UmsPermission umsPermission) {

        if(umsPermissionService.add(umsPermission))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("权限名已经存在");
    }
    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(umsPermissionService.getById(id));
    }
    @PostMapping("/update")
    CommonResult update(UmsPermission umsPermission) {
        return CommonResult.success(umsPermissionService.updateById(umsPermission));
    }
    @PostMapping("/del")
    CommonResult del(UmsPermission umsPermission) {
        umsPermission.setActive(0);
        return CommonResult.success(umsPermissionService.updateById(umsPermission));
    }

    @PostMapping("/back")
    CommonResult back(UmsPermission umsPermission) {
        umsPermission.setActive(1);
        return CommonResult.success(umsPermissionService.updateById(umsPermission));
    }





    @PostMapping("/userPermissionList")
    CommonResult userPermissionList(HttpServletRequest request) {

        String token = request.getHeader("token");
        Integer userId = JWT.decode(token).getClaim("id").asInt();
        return CommonResult.success(umsPermissionService.userPermissionList(userId));
    }







}
