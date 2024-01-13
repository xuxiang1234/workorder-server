package com.flyhigh.sms.domain.vo.waterMessageRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.flyhigh.common.utils.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("业务消息流水新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizWaterMessageRecordCreateReq extends BizWaterMessageRecordBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long id;

    @ApiModelProperty(value = "创建者", required = true)
    @NotNull(message = "创建者不能为空")
    private String createBy;

    @ApiModelProperty(value = "创建时间", required = true)
    @NotNull(message = "创建时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    @NotNull(message = "是否删除 0 否 1 是不能为空")
    private Integer delFlag;

}
