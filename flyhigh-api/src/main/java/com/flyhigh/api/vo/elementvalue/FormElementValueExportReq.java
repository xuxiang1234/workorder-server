package com.flyhigh.api.vo.elementvalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value = "表单元素值 Excel 导出 Request Req", description = "参数和 FormElementValuePageReq 是一致的")
@Data
public class FormElementValueExportReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "元素ID")
    private Long formElementId;

    @ApiModelProperty(value = "值索引")
    private Integer valueIndex;

    @ApiModelProperty(value = "元素值")
    private String value;

    @ApiModelProperty(value = "是否数字")
    private Integer isNumber;

}
