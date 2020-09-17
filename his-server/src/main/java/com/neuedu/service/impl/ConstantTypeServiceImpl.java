package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.ConstantTypeMapper;
import com.neuedu.pojo.ConstantType;
import com.neuedu.service.IConstantTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 常数类别 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class ConstantTypeServiceImpl extends ServiceImpl<ConstantTypeMapper, ConstantType> implements IConstantTypeService {

    @Override
    public Object list(ConstantType constantType) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<ConstantType> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(constantType.getName())) {
            wrapper.like("name",constantType.getName());
        }

        // 如果分页返回 IPage 如果不分页 返回 List
        if(constantType.getWithPage() == 1) {
            return this.page(new Page<>(constantType.getPageNo(),constantType.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(ConstantType constantType) {
        QueryWrapper<ConstantType> wrapper = new QueryWrapper<>();
        wrapper.eq("code",constantType.getCode());

        ConstantType query = this.getOne(wrapper);
        if(query == null )
            return this.save(constantType);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {

        ConstantType constantType = new ConstantType();
        constantType.setActive(0);

        UpdateWrapper<ConstantType> wrapper = new UpdateWrapper<>();
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        wrapper.in("id",list);


        return this.update(constantType,wrapper);
    }
}
