package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.pojo.UmsRole;
import com.neuedu.mapper.UmsRoleMapper;
import com.neuedu.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
    @Override
    public Object list(UmsRole umsRole) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(umsRole.getName())) {
            wrapper.like("name",umsRole.getName());
        }
        if(umsRole.getActive() != null) {
            wrapper.eq("active",umsRole.getActive());
        }
        // 如果分页返回 IPage 如果不分页 返回 List
        if(umsRole.getWithPage() == 1) {
            return this.page(new Page<>(umsRole.getPageNo(),umsRole.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(UmsRole umsRole) {
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        wrapper.eq("name",umsRole.getName());
        UmsRole query = this.getOne(wrapper);
        if(query == null )
            return this.save(umsRole);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        UmsRole role = new UmsRole();
        role.setActive(0);
        UpdateWrapper<UmsRole> wrapper = new UpdateWrapper<>();
        wrapper.in("id",list);
        return this.update(role,wrapper);
    }
}
