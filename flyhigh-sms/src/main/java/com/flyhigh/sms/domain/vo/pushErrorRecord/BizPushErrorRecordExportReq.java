package com.flyhigh.sms.domain.vo.pushErrorRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "推送异常 Excel 导出 Request Req", description = "参数和 BizPushErrorRecordPageReq 是一致的")
@Data
public class BizPushErrorRecordExportReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long messageId;

    @ApiModelProperty(value = "消息发送人数量")
    private Long userNumber;

    @ApiModelProperty(value = "重发次数")
    private Long tryTimes;

}
