package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.InspectApply;

import java.util.List;

/**
 * <p>
 * 检验申请 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IInspectApplyService extends IService<InspectApply> {

    boolean save(Integer registerId, Integer[] itemId);

    List<InspectApply> list(Integer registerId);
}
