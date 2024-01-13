package com.flyhigh.common.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下拉列表VO
 *
 * @author Mr.Lai
 * @date 2022/12/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("下拉列表VO")
public class ComboBoxVO {

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String label;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private String value;

}
