package com.flyhigh.sms.domain.vo.pushErrorRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("推送异常更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushErrorRecordUpdateReq extends BizPushErrorRecordBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

}
