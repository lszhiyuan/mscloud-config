package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.neuedu.pojo.ConstantItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 常数项表 Mapper 接口
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
public interface ConstantItemMapper extends BaseMapper<ConstantItem> {


    @Select("SELECT i.*,t.`name` type_name FROM constant_item i LEFT JOIN constant_type t ON i.`type_id` = t.`id` ${ew.customSqlSegment}")
    IPage<ConstantItem> list(IPage<ConstantItem> page, @Param(Constants.WRAPPER) Wrapper wrapper);


}
