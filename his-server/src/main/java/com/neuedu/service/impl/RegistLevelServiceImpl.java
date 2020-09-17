package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.RegistLevelMapper;
import com.neuedu.pojo.RegistLevel;
import com.neuedu.service.IRegistLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 挂号级别 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class RegistLevelServiceImpl extends ServiceImpl<RegistLevelMapper, RegistLevel> implements IRegistLevelService {
    @Override
    public Object list(RegistLevel registLevel) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<RegistLevel> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(registLevel.getName())) {
            wrapper.like("name",registLevel.getName());
        }
        if(registLevel.getActive() != null){
            wrapper.eq("active",registLevel.getActive());
        }

        // 如果分页返回 IPage 如果不分页 返回 List
        if(registLevel.getWithPage() == 1) {
            return this.page(new Page<>(registLevel.getPageNo(),registLevel.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }



    @Override
    public boolean batchdel(Integer[] ids) {

        RegistLevel registLevel = new RegistLevel();
        registLevel.setActive(0);

        UpdateWrapper<RegistLevel> wrapper = new UpdateWrapper<>();
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        wrapper.in("id",list);

        return this.update(registLevel,wrapper);
    }
}