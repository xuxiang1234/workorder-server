package com.flyhigh.sms.domain.vo.waterMessageRecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.flyhigh.common.utils.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 业务消息流水 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BizWaterMessageRecordBaseReq {

    @ApiModelProperty(value = "模块描述", required = true)
    @NotNull(message = "模块描述不能为空")
    private String moduleTypeDesc;

    @ApiModelProperty(value = "模块类型", required = true)
    @NotNull(message = "模块类型不能为空")
    private Long moduleType;

    @ApiModelProperty(value = "业务主键id", required = true)
    @NotNull(message = "业务主键id不能为空")
    private Long businessId;

    @ApiModelProperty(value = "消息类型")
    private Long messageType;

    @ApiModelProperty(value = "消息类型描述，防止一个模块多个消息类型")
    private String messageDesc;

    @ApiModelProperty(value = "自定义参数")
    private String customParameters;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息发送者", required = true)
    @NotNull(message = "消息发送者不能为空")
    private String messageSender;

    @ApiModelProperty(value = "创建人id", required = true)
    @NotNull(message = "创建人id不能为空")
    private Long createUserId;

    @ApiModelProperty(value = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @ApiModelProperty(value = "部门名称", required = true)
    @NotNull(message = "部门名称不能为空")
    private String deptName;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}



