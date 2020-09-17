package com.neuedu.service;

import com.neuedu.pojo.UmsPermission;
import com.neuedu.pojo.UmsRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与权限关系表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
public interface IUmsRolePermissionService extends IService<UmsRolePermission> {
    List<UmsPermission> getPermission();
    UmsRolePermission getByRoleId(Integer roleId);
}
