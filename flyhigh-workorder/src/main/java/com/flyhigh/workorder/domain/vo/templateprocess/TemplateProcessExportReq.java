package com.flyhigh.workorder.domain.vo.templateprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "模板流程 Excel 导出 Request Req", description = "参数和 templateProcessPageReq 是一致的")
@Data
public class TemplateProcessExportReq {

    @ApiModelProperty(value = "模板ID")
    private Long templateId;

    @ApiModelProperty(value = "流程类型: 人员 组 系统")
    private String processType;

    @ApiModelProperty(value = "流程位置 从1开始")
    private Integer processIndex;

    @ApiModelProperty(value = "组ID")
    private Long groupId;

    @ApiModelProperty(value = "指定人ID")
    private Long userId;

    @ApiModelProperty(value = "指定人名称")
    private String userName;

    @ApiModelProperty(value = "指定别名")
    private String aliasName;

}
