package com.flyhigh.sms.domain.vo.messageUserDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.flyhigh.common.utils.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("消息接收人新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMessageUserDetailCreateReq extends BizMessageUserDetailBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    @NotNull(message = "是否删除 0 否 1 是不能为空")
    private Integer delFlag;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

    @ApiModelProperty(value = "消息id", required = true)
    @NotNull(message = "消息id不能为空")
    private Long messageId;

    @ApiModelProperty(value = "用户id", required = true)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = " 状态 0未读 1已读", required = true)
    @NotNull(message = " 状态 0未读 1已读不能为空")
    private Integer receiveStatus;

    @ApiModelProperty(value = "是否处理审批 0 否 1是", required = true)
    @NotNull(message = "是否处理审批 0 否 1是不能为空")
    private Integer whetherApprove;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date updateTime;

}
