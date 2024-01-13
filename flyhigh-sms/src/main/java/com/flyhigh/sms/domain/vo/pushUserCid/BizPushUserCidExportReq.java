package com.flyhigh.sms.domain.vo.pushUserCid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "用户个推cid关系绑定 Excel 导出 Request Req", description = "参数和 BizPushUserCidPageReq 是一致的")
@Data
public class BizPushUserCidExportReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long userId;

    @ApiModelProperty(value = "个推cid")
    private String cid;

    @ApiModelProperty(value = "用户别名")
    private String userAlias;

}
