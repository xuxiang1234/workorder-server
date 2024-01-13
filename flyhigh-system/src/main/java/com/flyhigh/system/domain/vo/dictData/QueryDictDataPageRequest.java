package com.flyhigh.system.domain.vo.dictData;

import com.flyhigh.common.annotation.Excel;
import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询字典分页请求实体
 *
 * @description
 * @author: flyhigh
 * @create: 2022-02-23 09:20
 **/
@Data
@ApiModel("查询字典分页请求实体")
public class QueryDictDataPageRequest extends PageQuery {

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


    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

}
