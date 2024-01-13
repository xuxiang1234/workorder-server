package com.flyhigh.sms.domain.vo.pushTask;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("推送任务更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushTaskUpdateReq extends BizPushTaskBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;


}
