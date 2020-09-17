package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.InspectItem;

/**
 * <p>
 * 检验项目 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IInspectItemService extends IService<InspectItem> {
    Object list(InspectItem inspectItem);

    boolean add(InspectItem inspectItem);

    boolean batchdel(Integer[] ids);
}
