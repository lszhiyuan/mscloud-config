package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.neuedu.util.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 诊疗信息
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Register extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 挂号级别
     */
    private Integer regsitLevelId;

    /**
     * 挂号科室
     */
    private Integer deptId;

    /**
     * 看诊医生
     */
    private Integer doctorId;

    /**
     * 主诉
     */
    private String readme;

    /**
     * 现病史
     */
    private String present;

    /**
     * 现病史治疗情况
     */
    private String presentTreat;

    /**
     * 既往史
     */
    private String history;

    /**
     * 过敏史
     */
    private String allergy;

    /**
     * 是否要病历本
     */
    private Integer book;

    /**
     * 挂号费用
     */
    private BigDecimal fee;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否有效，1 有效，0 失效
     */
    private Integer active;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    @TableField(exist = false)
    private String genderName;
    @TableField(exist = false)
    private String levelName ;
    @TableField(exist = false)
    private String deptName ;
    @TableField(exist = false)
    private String  doctorName;

    /**
     * 看诊时间
     */
    private LocalDate  visittime;

    /**
     * 确诊疾病
     */
    private String disease;
    /**
     * 诊疗方案
     */
    private String suit;
    /**
     * 药品清单
     */
    private String drug;

}
