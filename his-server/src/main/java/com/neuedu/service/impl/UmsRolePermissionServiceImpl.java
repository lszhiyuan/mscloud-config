package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.pojo.UmsPermission;
import com.neuedu.pojo.UmsRolePermission;
import com.neuedu.mapper.UmsRolePermissionMapper;
import com.neuedu.service.IUmsPermissionService;
import com.neuedu.service.IUmsRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色与权限关系表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Service
public class UmsRolePermissionServiceImpl extends ServiceImpl<UmsRolePermissionMapper, UmsRolePermission> implements IUmsRolePermissionService {
    @Resource
    IUmsPermissionService umsPermissionService;
    @Override
    public List<UmsPermission> getPermission() {
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        wrapper.eq("active",1);
        List<UmsPermission> list = umsPermissionService.list(wrapper);
        for(UmsPermission permission : list) {
            getChildren(permission);
        }
        return list;
    }

    @Override
    public UmsRolePermission getByRoleId(Integer roleId) {
        QueryWrapper<UmsRolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        return this.getOne(wrapper);
    }

    /**
     * 参数为父权限对象
     * 通过父权限获取子权限
     *
     * */
    private void getChildren(UmsPermission umsPermission) {
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",umsPermission.getId());
        wrapper.eq("active",1);
        List<UmsPermission> list = umsPermissionService.list(wrapper);
        for(UmsPermission permission : list) {
//            if(permission.getType() != 0) {
                getChildren(permission);
//            }
        }
        umsPermission.setChildren(list);
    }
}
