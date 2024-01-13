package com.flyhigh.system.domain.vo.dictData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 删除字典类型请求参数
 *
 * @author flyhigh
 */
@Data
@ApiModel("删除字典请求实体")
public class DeleteDistDataRequest {

    /**
     * 字典类型id列表
     */
    @ApiModelProperty(value = "字典id列表")
    private List<Long> dictIds;
}
