package com.neuedu.service;

import com.neuedu.service.impl.RegisterServiceImpl;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/9/1  15:40 01
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :  serviceImple的代理对象
 */
public class RegisterServiceProxy extends RegisterServiceImpl implements IRegisterService {


    /**
     * com.neuedu.service.impl.*.update   在before之初，开启事务
     *
     * com.neuedu.service.impl.*.update   after，提交事务
     *
     * com.neuedu.service.impl.*.update   产生异常throwing，回滚事务
     *
     * @param checkApplyIds
     * @param inspectApplyIds
     * @return
     */
    @Override
    public boolean collectFee(Integer[] checkApplyIds, Integer[] inspectApplyIds) {


        //唱歌的方法
        try{
            //auto
            boolean success = super.collectFee(checkApplyIds, inspectApplyIds);
            //commit
        }catch(Exception ex){
            //回滚
        }


        //缴税
        //..............

        return false;
    }
}
