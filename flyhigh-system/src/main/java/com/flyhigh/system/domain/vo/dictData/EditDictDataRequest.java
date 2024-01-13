package com.flyhigh.system.domain.vo.dictData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 编辑字典类型请求实体
 *
 * @description
 * @author: flyhigh
 * @create: 2022-02-23 08:57
 **/
@Data
@ApiModel("编辑字典请求实体")
public class EditDictDataRequest extends AddDictDataRequest {
    /**
     * 字典编码
     */
    @ApiModelProperty("字典编码")
    private Long dictCode;

}
