package com.flyhigh.sms.domain.vo.waterMessageRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("业务消息流水更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizWaterMessageRecordUpdateReq extends BizWaterMessageRecordBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

}
