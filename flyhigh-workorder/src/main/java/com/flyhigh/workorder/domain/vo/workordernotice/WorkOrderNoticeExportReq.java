package com.flyhigh.workorder.domain.vo.workordernotice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "工单消息通知 Excel 导出 Request Req", description = "参数和 WorkOrderNoticePageReq 是一致的")
@Data
public class WorkOrderNoticeExportReq {

    @ApiModelProperty(value = "工单ID")
    private Long workOrderId;

    @ApiModelProperty(value = "工单名称")
    private String workOrderName;

    @ApiModelProperty(value = "是否已读")
    private Integer isRead;

}
