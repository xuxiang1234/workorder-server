package com.flyhigh.sms.domain.vo.pushErrorRecord;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("推送异常分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushErrorRecordPageReq extends PageQuery {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long messageId;

    @ApiModelProperty(value = "消息发送人数量")
    private Long userNumber;

    @ApiModelProperty(value = "重发次数")
    private Long tryTimes;

}
