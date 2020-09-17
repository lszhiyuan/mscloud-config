package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.CheckItemMapper;
import com.neuedu.pojo.CheckItem;
import com.neuedu.service.ICheckItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 检查项目 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {

    @Override
    public Object list(CheckItem checkItem) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(checkItem.getName())) {
            wrapper.like("name",checkItem.getName());
        }

        // 如果分页返回 IPage 如果不分页 返回 List
        if(checkItem.getWithPage() == 1) {
            return this.page(new Page<>(checkItem.getPageNo(),checkItem.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(CheckItem checkItem) {
        QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();
        wrapper.eq("name",checkItem.getName());

        CheckItem query = this.getOne(wrapper);
        if(query == null )
            return this.save(checkItem);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {

        CheckItem checkItem = new CheckItem();
        checkItem.setActive(0);

        UpdateWrapper<CheckItem> wrapper = new UpdateWrapper<>();
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        wrapper.in("id",list);

        return this.update(checkItem,wrapper);
    }
}
