package com.flyhigh.api.vo.workorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@ApiModel("工单更新 Req")
@Data
@ToString(callSuper = true)
public class WorkOrderUpdateReq {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "工单等级")
    private String workOrderLevel;

}
