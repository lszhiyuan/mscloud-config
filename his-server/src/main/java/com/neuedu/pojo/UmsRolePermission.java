package com.neuedu.pojo;

import com.neuedu.util.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与权限关系表
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UmsRolePermission extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String permissionId;


}
