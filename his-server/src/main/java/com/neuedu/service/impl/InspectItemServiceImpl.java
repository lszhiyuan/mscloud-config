package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.InspectItemMapper;
import com.neuedu.pojo.InspectItem;
import com.neuedu.service.IInspectItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 检验项目 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class InspectItemServiceImpl extends ServiceImpl<InspectItemMapper, InspectItem> implements IInspectItemService {

    @Override
    public Object list(InspectItem inspectItem) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<InspectItem> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(inspectItem.getName())) {
            wrapper.like("name",inspectItem.getName());
        }

        // 如果分页返回 IPage 如果不分页 返回 List
        if(inspectItem.getWithPage() == 1) {
            return this.page(new Page<>(inspectItem.getPageNo(),inspectItem.getPageSize()),wrapper);
        } else {
            return this.list(wrapper);
        }

    }

    @Override
    public boolean add(InspectItem inspectItem) {
        QueryWrapper<InspectItem> wrapper = new QueryWrapper<>();
        wrapper.eq("name",inspectItem.getName());

        InspectItem query = this.getOne(wrapper);
        if(query == null )
            return this.save(inspectItem);
        return false;
    }

    @Override
    public boolean batchdel(Integer[] ids) {

        InspectItem inspectItem = new InspectItem();
        inspectItem.setActive(0);

        UpdateWrapper<InspectItem> wrapper = new UpdateWrapper<>();
        List<Integer> list = new ArrayList<>(ids.length);
        Collections.addAll(list,ids);
        wrapper.in("id",list);

        return this.update(inspectItem,wrapper);
    }
}
