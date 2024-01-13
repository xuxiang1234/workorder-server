package com.flyhigh.sms.domain.vo.pushUserAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("用户个推别名更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushUserAliasUpdateReq extends BizPushUserAliasBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

}
