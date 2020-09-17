package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.DepartmentMapper;
import com.neuedu.pojo.Department;
import com.neuedu.service.IDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 科室 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Override
    public Object list(Department department) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<Department> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(department.getName())) {
            wrapper.like("name",department.getName());
        }
        if(department.getActive() != null){
            wrapper.eq("active",department.getActive());
        }
        // 如果分页返回 IPage 如果不分页 返回 List
        if(department.getWithPage() == 1) {
            return this.page(new Page<>(department.getPageNo(),department.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(Department department) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("name",department.getName());

        Department query = this.getOne(wrapper);
        if(query == null )
            return this.save(department);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {

        Department department = new Department();
        department.setActive(0);

        UpdateWrapper<Department> wrapper = new UpdateWrapper<>();
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        wrapper.in("id",list);


        return this.update(department,wrapper);
    }

}
