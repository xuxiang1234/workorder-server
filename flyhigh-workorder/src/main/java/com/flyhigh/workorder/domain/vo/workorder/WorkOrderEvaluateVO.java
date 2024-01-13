package com.flyhigh.workorder.domain.vo.workorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("工单dashbord统计 VO")
@Data
@ToString(callSuper = true)
public class WorkOrderEvaluateVO {

    @ApiModelProperty(value = "全部工单数", required = true)
    private Long allWorkOrderTotal;

    @ApiModelProperty(value = "已完成工单数", required = true)
    private Long okWorkOrderTotal;

    @ApiModelProperty(value = "未完成工单数", required = true)
    private Long nOkWorkOrderTotal;

    @ApiModelProperty(value = "待处理工单数", required = true)
    private Long nowWorkOrderTotal;

    @ApiModelProperty(value = "我创建工单数", required = true)
    private Long meWorkOrderTotal;

    @ApiModelProperty(value = "模板总数", required = true)
    private Long templateTotal;

}
