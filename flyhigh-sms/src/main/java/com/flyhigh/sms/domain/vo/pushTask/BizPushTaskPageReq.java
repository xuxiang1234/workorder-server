package com.flyhigh.sms.domain.vo.pushTask;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("推送任务分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushTaskPageReq extends PageQuery {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long messageId;

    @ApiModelProperty(value = "个推task_id")
    private String taskId;

    @ApiModelProperty(value = "个推code")
    private String gtCode;

    @ApiModelProperty(value = "消息发送人数量")
    private Long userNumber;

}
