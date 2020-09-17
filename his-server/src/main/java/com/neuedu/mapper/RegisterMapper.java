package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.neuedu.pojo.Register;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 诊疗信息 Mapper 接口
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface RegisterMapper extends BaseMapper<Register> {

    @Select("SELECT MAX(id)+1  FROM register")
    long maxNum();


    @Select("SELECT \n" +
            "  it.name gender_name,\n" +
            "  l.name level_name,\n" +
            "  dept.name dept_name,\n" +
            "  ur.realname doctor_name,\n" +
            "  r.id,\n" +
            "  r.name,\n" +
            "  r.gender,\n" +
            "  r.idno,\n" +
            "  r.birthday,\n" +
            "  r.age,\n" +
            "  r.address,\n" +
            "  r.regsit_level_id,\n" +
            "  r.dept_id,\n" +
            "  r.doctor_id,\n" +
            "  r.readme,\n" +
            "  r.present,\n" +
            "  r.present_treat,\n" +
            "  r.history,\n" +
            "  r.allergy,\n" +
            "  r.book,\n" +
            "  r.fee,\n" +
            "  r.status,\n" +
            "  r.active,\n" +
            "  r.createtime \n" +
            "FROM\n" +
            "  register r \n" +
            "  LEFT JOIN constant_item it \n" +
            "    ON r.gender = it.code \n" +
            "    AND type_id = 1 \n" +
            "  LEFT JOIN regist_level l \n" +
            "    ON r.regsit_level_id = l.id \n" +
            "  LEFT JOIN department dept \n" +
            "    ON r.dept_id = dept.id \n" +
            "  LEFT JOIN ums_user ur \n" +
            "    ON r.doctor_id = ur.id   ${ew.customSqlSegment}")
    IPage<Register> list(IPage<Register> page, @Param(Constants.WRAPPER) Wrapper wrapper);


}
