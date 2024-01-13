package com.flyhigh.sms.domain.vo.pushUserCid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("用户个推cid关系绑定更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushUserCidUpdateReq extends BizPushUserCidBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

}
