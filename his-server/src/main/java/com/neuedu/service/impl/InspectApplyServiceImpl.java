package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.mapper.InspectApplyMapper;
import com.neuedu.pojo.InspectApply;
import com.neuedu.pojo.InspectItem;
import com.neuedu.service.IInspectApplyService;
import com.neuedu.service.IInspectItemService;
import com.neuedu.util.HisConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 检验申请 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class InspectApplyServiceImpl extends ServiceImpl<InspectApplyMapper, InspectApply> implements IInspectApplyService {

    @Resource
    IInspectItemService inspectItemService;

    @Override
    public boolean save(Integer registerId, Integer[] itemIds) {

        //
        System.out.println("registerId"+registerId);

        QueryWrapper<InspectItem> wrapper = new QueryWrapper<>();
        wrapper.in("id",itemIds);

        List<InspectItem> items = inspectItemService.list(wrapper);

        List<InspectApply> applyList = new ArrayList<>();
        for (InspectItem item : items) {
            applyList.add(new InspectApply(
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
    public List<InspectApply> list(Integer registerId) {

        QueryWrapper<InspectApply> wrapper = new QueryWrapper<>();
        wrapper.eq("register_id",registerId);

        return this.list(wrapper);
    }
}
