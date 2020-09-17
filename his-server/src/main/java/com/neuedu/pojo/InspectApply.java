package com.neuedu.pojo;

import com.neuedu.util.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 检验申请
 * </p>
 *
 * @author jshand
 * @since 2020-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class InspectApply extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 病历号
     */
    private Integer registerId;

    /**
     * 项目id
     */
    private Integer itemId;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 检查费用
     */
    private BigDecimal fee;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否有效，1 有效，0 失效
     */
    private Integer active;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
