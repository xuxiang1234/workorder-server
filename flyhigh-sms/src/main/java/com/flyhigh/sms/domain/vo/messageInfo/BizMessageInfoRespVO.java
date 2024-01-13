package com.flyhigh.sms.domain.vo.messageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("消息 Response VO")
@Data
@ToString(callSuper = true)
public class BizMessageInfoRespVO {

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long id;

    @ApiModelProperty(value = "模块", required = true)
    private String moduleType;

    @ApiModelProperty(value = "业务主键id", required = true)
    private Long businessId;

    @ApiModelProperty(value = "消息类型", required = true)
    private String messageType;

    @ApiModelProperty(value = "组件路径", required = true)
    private String component;

    @ApiModelProperty(value = "自定义参数", required = true)
    private String customParameters;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

    @ApiModelProperty(value = "消息发送者", required = true)
    private String messageSender;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新者", required = true)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    private Integer delFlag;

}
