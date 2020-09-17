package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.dto.FeeDTO;
import com.neuedu.mapper.RegisterMapper;
import com.neuedu.pojo.CheckApply;
import com.neuedu.pojo.InspectApply;
import com.neuedu.pojo.RegistLevel;
import com.neuedu.pojo.Register;
import com.neuedu.service.ICheckApplyService;
import com.neuedu.service.IInspectApplyService;
import com.neuedu.service.IRegistLevelService;
import com.neuedu.service.IRegisterService;
import com.neuedu.util.HisConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 诊疗信息 服务实现类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {

    @Resource
    IRegistLevelService registLevelService;


    @Resource
    IRegisterService registerService;

    @Resource
    ICheckApplyService checkApplyService;

    @Resource
    IInspectApplyService inspectApplyService;


    /**
     * 挂号
     * @param register
     * @return
     */
    @Override
    public boolean add(Register register) {
        register.setStatus(HisConstants.REGISTER_REGIST); //默认添加的数据为已挂号状态

        //根据挂号级别 和是否需要病历本计算 挂号费用

        RegistLevel registLevel = registLevelService.getById(register.getRegsitLevelId());

        BigDecimal fee = registLevel.getFee();
        if(1 == register.getBook()){
            fee.add(new BigDecimal(1));
        }
        register.setFee(fee);
        return this.save(register);
    }



    @Override
    public Object list(Register register) {
        // 如果 有name传过来 就按照name 模糊查询
        QueryWrapper<Register> wrapper = new QueryWrapper<>();

        // 如果分页返回 IPage 如果不分页 返回 List
        if(register.getWithPage() == 1) {
            wrapper.orderByDesc("r.createtime");

            if(StringUtils.isNotBlank(register.getName())) {
                wrapper.like("r.name",register.getName());
            }
            if(register.getActive() != null) {
                wrapper.eq("r.active",register.getActive());
            }
            if(register.getId() != null) {
                wrapper.eq("r.id",register.getId());
            }

//            return this.page(new Page<>(register.getPageNo(),register.getPageSize()),wrapper);
            return this.getBaseMapper().list(new Page<>(register.getPageNo(),register.getPageSize()),wrapper);
        } else {
            wrapper.orderByDesc("createtime");

            if(StringUtils.isNotBlank(register.getName())) {
                wrapper.like("name",register.getName());
            }
            if(register.getActive() != null) {
                wrapper.eq("active",register.getActive());
            }
            return this.list(wrapper);
        }

    }

    @Override
    public long maxNum() {
        return getBaseMapper().maxNum();
    }

    /**
     * 1 挂号的姓名、年龄、身份证号 -- Register POJO
     * 2 检查项目 List
     * 3 检验的项目 List
     */
    @Override
    public FeeDTO showFee(Integer registerId) {

        FeeDTO dto = new FeeDTO();

        //1 挂号的姓名、年龄、身份证号 -- Register POJO
        Register register = registerService.getById(registerId);

        //2 检查项目 List
        QueryWrapper<CheckApply> checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("register_id",registerId);
        List<CheckApply> checkApplyList = checkApplyService.list(checkWrapper);

        //3 检验项目 List
        QueryWrapper<InspectApply> inspectWrapper = new QueryWrapper<>();
        inspectWrapper.eq("register_id",registerId);
        List<InspectApply> inspectApplyList = inspectApplyService.list(inspectWrapper);

        dto.setRegister(register);
        dto.setCheckApplyList(checkApplyList);
        dto.setInspectApplyList(inspectApplyList);


        return dto;
    }


    /**
     * Connection con = null;
     *         try {
     *             //开启事务
     *             con.setAutoCommit(false);
     *
     *             //执行多次操作更新、insert delete
     *             //保证同时成功，或者同时失败
     *
     *              collectFee
     *
     *
     *             con.commit(); //提交事务
     *         } catch (SQLException e) {
     *             con.rollback(); //回滚
     *             e.printStackTrace();
     *         }
     * @param checkApplyIds
     * @param inspectApplyIds
     * @return
     */

    @Override
//    @Transactional(rollbackFor=RuntimeException.class,isolation= Isolation.DEFAULT,propagation = Propagation.NEVER)  //添加事务
    @Transactional
    public boolean collectFee(Integer[] checkApplyIds, Integer[] inspectApplyIds) {
        //事务
        //事务
        boolean success1 = true;
        boolean success2 = true;
        if(checkApplyIds != null && checkApplyIds.length>0) {
            CheckApply checkApply = new CheckApply();
            checkApply.setStatus(HisConstants.CHECK_APPLY_STATUS_2);
            UpdateWrapper<CheckApply> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.in("id", checkApplyIds);
            success1 = checkApplyService.update(checkApply, updateWrapper1);
        }

        if(inspectApplyIds != null && inspectApplyIds.length>0) {
            InspectApply inspectApply = new InspectApply();
            inspectApply.setStatus(HisConstants.INSPECT_APPLY_STATUS_2);
            UpdateWrapper<InspectApply> updateWrapper2 = new UpdateWrapper<>();
            updateWrapper2.in("id",inspectApplyIds);
            success2 = inspectApplyService.update(inspectApply,updateWrapper2);
        }

        return success1 && success2;
    }





    @Override
    @Transactional
    public boolean refund(Integer[] checkApplyIds, Integer[] inspectApplyIds) {
        //事务
        boolean success1 = true;
        boolean success2 = true;
        if(checkApplyIds != null && checkApplyIds.length>0) {
            CheckApply checkApply = new CheckApply();
            checkApply.setStatus(HisConstants.CHECK_APPLY_STATUS_4);
            UpdateWrapper<CheckApply> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.in("id", checkApplyIds);
            success1 = checkApplyService.update(checkApply, updateWrapper1);
        }

        if(inspectApplyIds != null && inspectApplyIds.length>0) {
            InspectApply inspectApply = new InspectApply();
            inspectApply.setStatus(HisConstants.INSPECT_APPLY_STATUS_4);
            UpdateWrapper<InspectApply> updateWrapper2 = new UpdateWrapper<>();
            updateWrapper2.in("id",inspectApplyIds);
            success2 = inspectApplyService.update(inspectApply,updateWrapper2);
        }

        return success1 && success2;
    }
}
