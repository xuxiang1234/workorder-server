package com.flyhigh.api.vo.element;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value = "表单元素 Excel 导出 Request Req", description = "参数和 FormElementPageReq 是一致的")
@Data
public class FormElementExportReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表单ID")
    private Long formId;

    @ApiModelProperty(value = "元素CODE 同一表单CODE唯一")
    private String code;

    @ApiModelProperty(value = "元素标题")
    private String label;

    @ApiModelProperty(value = "元素类型")
    private String type;

    @ApiModelProperty(value = "业务名称")
    private String bizName;

    @ApiModelProperty(value = "业务字段名")
    private String bizField;

    @ApiModelProperty(value = "业务字段分组key")
    private String bizKey;

}
