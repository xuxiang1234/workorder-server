package com.flyhigh.workorder.domain.vo.templateprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ApiModel("模板流程更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateProcessUpdateReq extends TemplateProcessBaseReq {

    @ApiModelProperty(value = "表单模板内容")
    private String formTemplateContent;

    @ApiModelProperty(value = "是否可多填表单")
    private Integer isMultForm;

}
