package com.flyhigh.api.vo.workorder;

import com.flyhigh.common.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 工单 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WorkOrderBaseReq implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "工单名称", required = true)
    @NotBlank(message = "工单名称不能为空")
    private String workOrderName;

    @ApiModelProperty(value = "工单类型", required = true)
    @NotBlank(message = "工单类型不能为空")
    private String workOrderType;

    @ApiModelProperty(value = "工单等级")
    private String workOrderLevel;

    /**
     * 预计开始时间
     */
    @ApiModelProperty(value = "预计开始时间", required = true)
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date startTime;


    @ApiModelProperty(value = "设备名称")
    private String deviceName;


    @ApiModelProperty(value = "位置")
    private String position;


    @ApiModelProperty(value = "来源")
    private String source;


    @ApiModelProperty(value = "工单描述")
    private String description;


    @ApiModelProperty(value = "工单图片地址 多个逗号分隔")
    private String picUrl;


    @ApiModelProperty(value = "报价")
    private String offer;


    @ApiModelProperty(value = "科室")
    private String office;


    @ApiModelProperty(value = "报修人")
    private String repairName;


    @ApiModelProperty(value = "报修人ID")
    private String repairId;


}



