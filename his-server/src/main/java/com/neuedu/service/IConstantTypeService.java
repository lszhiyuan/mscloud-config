package com.neuedu.service;

import com.neuedu.pojo.ConstantType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 常数类别 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IConstantTypeService extends IService<ConstantType> {

    Object list(ConstantType constantType);

    boolean add(ConstantType constantType);

    boolean batchdel(Integer[] ids);
}
