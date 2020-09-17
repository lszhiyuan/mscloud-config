package com.neuedu.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePojo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 页号
    @JsonIgnore
    @TableField(exist = false)
    private Integer pageNo = 1;
    // 每页大小
    @JsonIgnore
    @TableField(exist = false)
    private Integer pageSize = 6;
    // 是否分页  1分页 0不分
    @JsonIgnore
    @TableField(exist = false)
    private Integer withPage = 1;
}
