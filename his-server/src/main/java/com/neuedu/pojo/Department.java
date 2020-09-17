package com.neuedu.pojo;

import com.neuedu.util.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 科室
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Department extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 负责人
     */
    private String leader;
    /**
     * 部门类型
     */
    private Integer type;

    /**
     * 是否有效，1 有效，0 失效
     */
    private Integer active;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
