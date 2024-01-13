package com.flyhigh.api.vo.workorder;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("工单分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderPageReq extends PageQuery {

    @ApiModelProperty(value = "工单状态")
    @NotNull(message = "工单状态不能为空")
    private ProcessPageStatusReq status;

    @ApiModelProperty(value = "模糊匹配工单编号，工单名称，设备名称，位置")
    private String keyword;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "工单名称")
    private String workOrderName;

    @ApiModelProperty(value = "工单类型")
    private String workOrderType;

    @ApiModelProperty(value = "工单等级")
    private String workOrderLevel;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "位置")
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间开始")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间结束")
    private Date endTime;

}
