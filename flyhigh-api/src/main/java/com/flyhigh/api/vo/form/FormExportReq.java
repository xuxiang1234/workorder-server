package com.flyhigh.api.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value = "单 Excel 导出 Request Req", description = "参数和 FormPageReq 是一致的")
@Data
public class FormExportReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联ID")
    private Long contactId;

    @ApiModelProperty(value = "系统 保证每个系统数据不冲突")
    private String system;

    @ApiModelProperty(value = "表单内容")
    private String content;

}
