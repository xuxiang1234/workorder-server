package com.flyhigh.sms.domain.vo.pushTask;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("推送任务 Response VO")
@Data
@ToString(callSuper = true)
public class BizPushTaskRespVO {

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long id;

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long messageId;

    @ApiModelProperty(value = "个推task_id", required = true)
    private String taskId;

    @ApiModelProperty(value = "个推code", required = true)
    private String gtCode;

    @ApiModelProperty(value = "消息发送人数量", required = true)
    private Long userNumber;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    private Integer delFlag;

}
