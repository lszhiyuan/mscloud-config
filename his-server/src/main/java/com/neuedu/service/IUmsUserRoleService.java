package com.neuedu.service;

import com.neuedu.pojo.UmsUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户与角色关系表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
public interface IUmsUserRoleService extends IService<UmsUserRole> {
     UmsUserRole getByUserId(Integer userId);
}
