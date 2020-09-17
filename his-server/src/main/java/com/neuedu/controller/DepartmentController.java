package com.neuedu.controller;


import com.neuedu.pojo.Department;
import com.neuedu.service.IDepartmentService;
import com.neuedu.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 科室 前端控制器
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    IDepartmentService departmentService;

    @GetMapping("/list")
    CommonResult list(Department department) {
        return CommonResult.success(departmentService.list(department));
    }


    @PostMapping("/add")
    CommonResult add(Department department) {
        if(departmentService.add(department))
            return CommonResult.success("ok");
        else
            return CommonResult.failed("部门已经存在");
    }



    @GetMapping("/getById")
    CommonResult getById(Integer id) {
        return CommonResult.success(departmentService.getById(id));
    }


    @PostMapping("/update")
    CommonResult update(Department department) {
        return CommonResult.success(departmentService.updateById(department));
    }


    /**
     * 逻辑删，修改状态 active 0
     * @param department
     * @return
     */
    @PostMapping("/del")
    CommonResult del(Department department) {
        department.setActive(0);
        return CommonResult.success(departmentService.updateById(department));
    }


    @PostMapping("/batchdel")
    CommonResult batchdel(Integer[] ids) {
        return CommonResult.success(departmentService.batchdel(ids));
    }


    @PostMapping("/back")
    CommonResult back(Department department) {
        department.setActive(1);
        return CommonResult.success(departmentService.updateById(department));
    }
}
