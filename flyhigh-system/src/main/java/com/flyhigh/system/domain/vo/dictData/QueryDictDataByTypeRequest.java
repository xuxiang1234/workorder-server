package com.flyhigh.system.domain.vo.dictData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询字典列表请求实体
 *
 * @author flyhigh
 */
@Data
@ApiModel("查询字典列表请求实体")
public class QueryDictDataByTypeRequest {

    /**
     * 字典类型
     */
    @ApiModelProperty("字典类型")
    private String dictType;

    /**
     * 父级字典id
     */
    @ApiModelProperty("父级字典id")
    private Long parentDictCode;
}
