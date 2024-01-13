package com.flyhigh.sms.domain.vo.messageInfo;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("消息分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMessageInfoPageReq extends PageQuery {

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
