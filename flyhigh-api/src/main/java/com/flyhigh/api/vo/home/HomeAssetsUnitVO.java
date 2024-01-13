package com.flyhigh.api.vo.home;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产单元统计VO
 *
 * @author Mr.Lai
 * @date 2023/4/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeAssetsUnitVO {

    /**
     * 总资产单元数
     */
    @ApiModelProperty(value = "总资产单元数")
    private Long total;

    /**
     * 已租单元数
     */
    @ApiModelProperty(value = "已租单元数")
    private Long used;

    /**
     * 待租单元数
     */
    @ApiModelProperty(value = "待租单元数")
    private Long unused;

}
