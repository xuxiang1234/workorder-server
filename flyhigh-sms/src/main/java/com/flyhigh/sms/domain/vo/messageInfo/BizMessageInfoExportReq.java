package com.flyhigh.sms.domain.vo.messageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "消息 Excel 导出 Request Req", description = "参数和 BizMessageInfoPageReq 是一致的")
@Data
public class BizMessageInfoExportReq {

    @ApiModelProperty(value = "模块")
    private String moduleType;

    @ApiModelProperty(value = "业务主键id")
    private Long businessId;

    @ApiModelProperty(value = "消息类型")
    private String messageType;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "自定义参数")
    private String customParameters;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息发送者")
    private String messageSender;

}
