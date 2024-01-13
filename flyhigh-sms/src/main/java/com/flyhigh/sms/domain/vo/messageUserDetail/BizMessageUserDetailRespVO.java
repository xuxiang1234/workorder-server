package com.flyhigh.sms.domain.vo.messageUserDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("消息接收人 Response VO")
@Data
@ToString(callSuper = true)
public class BizMessageUserDetailRespVO {

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long id;

    @ApiModelProperty(value = "消息id", required = true)
    private Long messageId;

    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty(value = " 状态 0未读 1已读", required = true)
    private Integer receiveStatus;

    @ApiModelProperty(value = "是否处理审批 0 否 1是", required = true)
    private Integer whetherApprove;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    private Integer delFlag;

    @ApiModelProperty(value = "创建者", required = true)
    private String createBy;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新者", required = true)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
