package com.neuedu.pojo;

import com.neuedu.util.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String username;


    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 医生类型
     */
    private Integer userType;

    /**
     * 医生挂号级别
     */
    private Integer registlevel;

    /**
     * 归属部门
     */
    private Integer deptId;

    /**
     * 最后一次登录时间
     */
    private LocalDateTime lastlogin;

    /**
     * 是否有效 1有效 0失效
     */
    private Integer active;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
