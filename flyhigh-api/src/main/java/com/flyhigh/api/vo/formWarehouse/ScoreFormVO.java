package com.flyhigh.api.vo.formWarehouse;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评分表单
 *
 * @author Mr.Lai
 * @date 2023/5/5
 */
@Data
@ExcelIgnoreUnannotated
public class ScoreFormVO extends FormWarehouseVO {

    /**
     * 激活状态：true 激活、false 未激活
     */
    @ApiModelProperty("激活状态：true 激活、false 未激活")
    private Boolean isActive;

}
