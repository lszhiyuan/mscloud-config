package com.neuedu.service;

import com.neuedu.pojo.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-08-19
 */
public interface IUmsRoleService extends IService<UmsRole> {
    Object list(UmsRole umsRole);
    boolean add(UmsRole umsRole);
    boolean batchdel(Integer[] ids);
}
