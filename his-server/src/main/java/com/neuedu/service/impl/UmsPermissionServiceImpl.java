package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.UmsPermissionMapper;
import com.neuedu.pojo.UmsPermission;
import com.neuedu.pojo.UmsRolePermission;
import com.neuedu.pojo.UmsUserRole;
import com.neuedu.service.IUmsPermissionService;
import com.neuedu.service.IUmsRolePermissionService;
import com.neuedu.service.IUmsUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IUmsPermissionService {


    @Resource
    IUmsUserRoleService umsUserRoleService;

    @Resource
    IUmsRolePermissionService umsRolePermissionService;




    @Override
    public Object list(UmsPermission umsPermission) {
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        IPage<UmsPermission> page = this.page(new Page<>(umsPermission.getPageNo(),umsPermission.getPageSize()),wrapper);
        List<UmsPermission> list = page.getRecords();
        for(UmsPermission permission : list) {
            getChildren(permission);
        }
        return page;
    }

    @Override
    public boolean add(UmsPermission umsPermission) {
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("name",umsPermission.getName());
        UmsPermission query = this.getOne(wrapper);
        if(query == null )
            return this.save(umsPermission);
        return false;
    }




    /**
     * 参数为父权限对象
     * 通过父权限获取子权限
     *
     * */
    private void getChildren(UmsPermission umsPermission) {
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",umsPermission.getId());
        List<UmsPermission> list = this.list(wrapper);
        for(UmsPermission permission : list) {
//            if(permission.getType() != 0) {
                getChildren(permission);
//            }
        }
        umsPermission.setChildren(list);
    }


    @Override
    public List<UmsPermission> userPermissionList(Integer userId) {
        //1根据用户id查询出所有的角色ums_user_role
        QueryWrapper<UmsUserRole> umsUserQueryWrapper = new QueryWrapper<>();
        umsUserQueryWrapper.eq("user_id",userId);
        UmsUserRole umsUserRole = umsUserRoleService.getOne(umsUserQueryWrapper);
        if(umsUserRole==null || umsUserRole.getRoleId() == null){
            return null;
        }

        //System.out.println(umsUserRole.getRoleId()); //
        String[] roleIds = umsUserRole.getRoleId().split(",");
        //根据角色获取权限id集合
        QueryWrapper<UmsRolePermission> umsRolePermissionWrapper = new QueryWrapper<>();
        umsRolePermissionWrapper.in("role_id",roleIds);
        List<UmsRolePermission> rolePermissionList = umsRolePermissionService.list(umsRolePermissionWrapper);
        if(rolePermissionList ==null ){
            return null;
        }

        Set permissionIdSet = new HashSet();
        for (UmsRolePermission umsRolePermission : rolePermissionList) {
            permissionIdSet.addAll(Arrays.asList(umsRolePermission.getPermissionId().split(",")));
        }

//        permissionIdSet.forEach(System.out::println);

        //根据权限id查询权限列表
        QueryWrapper<UmsPermission> umsPermissionQueryWrapper = new QueryWrapper<>();
        umsPermissionQueryWrapper.in("id",permissionIdSet);
        List<UmsPermission> allPermission = this.list(umsPermissionQueryWrapper);
        //目标，将顶层菜单放入
        List<UmsPermission> frontPermissionList = new ArrayList();
        for (UmsPermission permission : allPermission) {
            if(permission.getParentId() == 0){
                setChildrenPermission(permission,allPermission);
                frontPermissionList.add(permission);
            }
        }
        return frontPermissionList;
    }

    private void setChildrenPermission(UmsPermission permission, List<UmsPermission> allPermission) {
        List<UmsPermission> children = new ArrayList();
        for (UmsPermission umsPermission : allPermission) {
            if(permission.getId() == umsPermission.getParentId()){
                setChildrenPermission(umsPermission,allPermission);
                children.add(umsPermission);
            }
        }
        permission.setChildren(children);
    }



    @Override
    public List<UmsPermission> userPermissionListValidate(Integer userId) {
        //1根据用户id查询出所有的角色ums_user_role
        QueryWrapper<UmsUserRole> umsUserQueryWrapper = new QueryWrapper<>();
        umsUserQueryWrapper.eq("user_id",userId);
        UmsUserRole umsUserRole = umsUserRoleService.getOne(umsUserQueryWrapper);
        if(umsUserRole==null || umsUserRole.getRoleId() == null){
            return null;
        }

        //System.out.println(umsUserRole.getRoleId()); //
        String[] roleIds = umsUserRole.getRoleId().split(",");
        //根据角色获取权限id集合
        QueryWrapper<UmsRolePermission> umsRolePermissionWrapper = new QueryWrapper<>();
        umsRolePermissionWrapper.in("role_id",roleIds);
        List<UmsRolePermission> rolePermissionList = umsRolePermissionService.list(umsRolePermissionWrapper);
        if(rolePermissionList ==null ){
            return null;
        }

        Set permissionIdSet = new HashSet();
        for (UmsRolePermission umsRolePermission : rolePermissionList) {
            permissionIdSet.addAll(Arrays.asList(umsRolePermission.getPermissionId().split(",")));
        }

        //根据权限id查询权限列表
        QueryWrapper<UmsPermission> umsPermissionQueryWrapper = new QueryWrapper<>();
        umsPermissionQueryWrapper.in("id",permissionIdSet);
        List<UmsPermission> allPermission = this.list(umsPermissionQueryWrapper);
        return allPermission;
    }

}
