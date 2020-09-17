package com.neuedu.service;

import com.neuedu.pojo.CheckApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 检查申请 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface ICheckApplyService extends IService<CheckApply> {

    boolean save(Integer registerId, Integer[] itemId);

    List<CheckApply> list(Integer registerId);
}
