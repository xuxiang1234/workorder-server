package com.flyhigh.workorder.domain.vo.workordernotice;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("工单消息通知分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderNoticePageReq extends PageQuery {

    @ApiModelProperty(value = "工单ID")
    private Long workOrderId;

    @ApiModelProperty(value = "工单名称")
    private String workOrderName;

    @ApiModelProperty(value = "是否已读")
    private Integer isRead;

    private Long userId;

}
