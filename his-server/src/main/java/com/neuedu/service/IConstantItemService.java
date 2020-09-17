package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.ConstantItem;

/**
 * <p>
 * 常数项表 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IConstantItemService extends IService<ConstantItem> {
    Object list(ConstantItem constantItem);

    boolean add(ConstantItem constantItem);

    boolean batchdel(Integer[] ids);
}
