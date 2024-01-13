package com.flyhigh.sms.domain.vo.waterMessageRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "业务消息流水 Excel 导出 Request Req", description = "参数和 BizWaterMessageRecordPageReq 是一致的")
@Data
public class BizWaterMessageRecordExportReq {

    @ApiModelProperty(value = "模块描述")
    private String moduleTypeDesc;

    @ApiModelProperty(value = "模块类型")
    private Long moduleType;

    @ApiModelProperty(value = "业务主键id")
    private Long businessId;

    @ApiModelProperty(value = "消息类型")
    private Long messageType;

    @ApiModelProperty(value = "消息类型描述，防止一个模块多个消息类型")
    private String messageDesc;

    @ApiModelProperty(value = "自定义参数")
    private String customParameters;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息发送者")
    private String messageSender;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

}
