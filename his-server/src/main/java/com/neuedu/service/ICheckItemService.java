package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.CheckItem;

/**
 * <p>
 * 检查项目 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface ICheckItemService extends IService<CheckItem> {

    Object list(CheckItem checkItem);

    boolean add(CheckItem checkItem);

    boolean batchdel(Integer[] ids);

}
