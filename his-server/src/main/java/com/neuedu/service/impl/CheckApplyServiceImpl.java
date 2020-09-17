package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.CheckApplyMapper;
import com.neuedu.pojo.CheckApply;
import com.neuedu.pojo.CheckItem;
import com.neuedu.service.ICheckApplyService;
import com.neuedu.service.ICheckItemService;
import com.neuedu.util.HisConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 检查申请 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class CheckApplyServiceImpl extends ServiceImpl<CheckApplyMapper, CheckApply> implements ICheckApplyService {

    @Resource
    ICheckItemService checkItemService;

    @Override
    public boolean save(Integer registerId, Integer[] itemIds) {

        //
        System.out.println("registerId"+registerId);

        QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();
        wrapper.in("id",itemIds);

        List<CheckItem> items = checkItemService.list(wrapper);
        List<CheckApply> applyList = new ArrayList<>();
        for (CheckItem item : items) {
            applyList.add(new CheckApply(
                    registerId,
                    item.getId(),
                    item.getName(),
                    item.getFee(),
                    HisConstants.CHECK_APPLY_STATUS_1,
                    null,
                    null));

        }

        return this.saveBatch(applyList);

    }

    @Override
    public List<CheckApply> list(Integer registerId) {
        QueryWrapper<CheckApply> wrapper = new QueryWrapper<>();
        wrapper.eq("register_id",registerId);

        return this.list(wrapper);
    }
}
