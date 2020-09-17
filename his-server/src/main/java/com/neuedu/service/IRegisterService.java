package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.dto.FeeDTO;
import com.neuedu.pojo.Register;

/**
 * <p>
 * 诊疗信息 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IRegisterService extends IService<Register> {

    boolean add(Register register);

    Object list(Register register);

    long maxNum();

    //展示代收费的信息
    /**
     * 1 挂号的姓名、年龄、身份证号 -- Register POJO
     * 2 检查项目 List
     * 3 检验的项目 List
     */
    FeeDTO showFee(Integer registerId);


    boolean collectFee(Integer[] checkApplyIds, Integer[] inspectApplyIds);
    boolean refund(Integer[] checkApplyIds, Integer[] inspectApplyIds);
}
