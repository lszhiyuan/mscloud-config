package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.neuedu.util.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UmsPermission extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 0-按钮 1-导航
     */
    private Integer type;

    /**
     * 上级id,最高级上级为0
     */
    private Integer parentId;

    private Integer active;

    @TableField(exist = false)
    private List<UmsPermission> children;

}
