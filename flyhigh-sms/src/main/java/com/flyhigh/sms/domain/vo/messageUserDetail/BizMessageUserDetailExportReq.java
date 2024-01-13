package com.flyhigh.sms.domain.vo.messageUserDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "消息接收人 Excel 导出 Request Req", description = "参数和 BizMessageUserDetailPageReq 是一致的")
@Data
public class BizMessageUserDetailExportReq {

    @ApiModelProperty(value = "消息id")
    private Long messageId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = " 状态 0未读 1已读")
    private Integer receiveStatus;

    @ApiModelProperty(value = "是否处理审批 0 否 1是")
    private Integer whetherApprove;

}
