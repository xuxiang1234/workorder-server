package com.flyhigh.sms.domain.vo.pushUserAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "用户个推别名 Excel 导出 Request Req", description = "参数和 BizPushUserAliasPageReq 是一致的")
@Data
public class BizPushUserAliasExportReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long userId;

    @ApiModelProperty(value = "用户别名")
    private String userAlias;

}
