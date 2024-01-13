package com.flyhigh.workorder.domain.vo.workordernotice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 工单消息通知 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WorkOrderNoticeBaseReq {

    @ApiModelProperty(value = "工单ID", required = true)
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;


    @ApiModelProperty(value = "工单名称", required = true)
    @NotNull(message = "工单名称不能为空")
    private String workOrderName;


    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;


    @ApiModelProperty(value = "是否已读", required = true)
    @NotNull(message = "是否已读不能为空")
    private Integer isRead;


}



