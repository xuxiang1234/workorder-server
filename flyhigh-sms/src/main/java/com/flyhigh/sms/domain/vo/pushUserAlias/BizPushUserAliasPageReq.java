package com.flyhigh.sms.domain.vo.pushUserAlias;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("用户个推别名分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizPushUserAliasPageReq extends PageQuery {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long userId;

    @ApiModelProperty(value = "用户别名")
    private String userAlias;

}
