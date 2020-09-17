package com.neuedu.dto;

import com.neuedu.pojo.CheckApply;
import com.neuedu.pojo.InspectApply;
import com.neuedu.pojo.Register;
import lombok.Data;

import java.util.List;

/**
 * 项目    ： his-server
 * 创建时间 ：2020/9/1  14:16 01
 * author  ：jshand
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Data
public class FeeDTO {

    private Register register;
    private List<CheckApply> checkApplyList;
    private List<InspectApply> inspectApplyList;




}
