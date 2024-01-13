package com.flyhigh.workorder.domain.vo.workorderprocess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "工单流程 Excel 导出 Request Req", description = "参数和 WorkOrderProcessPageReq 是一致的")
@Data
public class WorkOrderProcessExportReq {

    @ApiModelProperty(value = "工单ID")
    private Long workOrderId;

    @ApiModelProperty(value = "流程类型: 人员 组 系统")
    private String processType;

    @ApiModelProperty(value = "流程位置 从1开始")
    private Integer processIndex;

    @ApiModelProperty(value = "工单流程状态 驳回 确认")
    private String status;

    @ApiModelProperty(value = "评价")
    private String evaluate;

    @ApiModelProperty(value = "评价图片")
    private String evaluatePicUrl;

    @ApiModelProperty(value = "组ID")
    private Long groupId;

    @ApiModelProperty(value = "指定人ID")
    private Long userId;

    @ApiModelProperty(value = "指定人名称")
    private String userName;

    @ApiModelProperty(value = "指定别名")
    private String aliasName;

}
