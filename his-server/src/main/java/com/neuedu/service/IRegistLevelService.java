package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.RegistLevel;

/**
 * <p>
 * 挂号级别 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IRegistLevelService extends IService<RegistLevel> {
    Object list(RegistLevel registLevel);

    boolean batchdel(Integer[] ids);
}
