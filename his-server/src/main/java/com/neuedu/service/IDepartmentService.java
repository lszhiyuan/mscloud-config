package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.Department;

/**
 * <p>
 * 科室 服务类
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface IDepartmentService extends IService<Department> {
    Object list(Department department);

    boolean add(Department department);

    boolean batchdel(Integer[] ids);
}
