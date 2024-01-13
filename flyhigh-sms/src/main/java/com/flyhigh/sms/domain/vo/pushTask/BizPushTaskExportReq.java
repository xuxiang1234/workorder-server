package com.flyhigh.sms.domain.vo.pushTask;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "推送任务 Excel 导出 Request Req", description = "参数和 BizPushTaskPageReq 是一致的")
@Data
public class BizPushTaskExportReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long messageId;

    @ApiModelProperty(value = "个推task_id")
    private String taskId;

    @ApiModelProperty(value = "个推code")
    private String gtCode;

    @ApiModelProperty(value = "消息发送人数量")
    private Long userNumber;

}
