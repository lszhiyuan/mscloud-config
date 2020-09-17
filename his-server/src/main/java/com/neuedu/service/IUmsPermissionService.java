package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.UmsPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
public interface IUmsPermissionService extends IService<UmsPermission> {
    Object list(UmsPermission umsPermission);
    boolean add(UmsPermission umsPermission);

    List<UmsPermission> userPermissionList(Integer userId);

    List<UmsPermission> userPermissionListValidate(Integer userId);

}
