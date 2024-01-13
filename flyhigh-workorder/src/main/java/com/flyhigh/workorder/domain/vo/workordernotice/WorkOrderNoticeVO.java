package com.flyhigh.workorder.domain.vo.workordernotice;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 工单消息通知视图对象 work_order_notice
 *
 * @author flyhigh
 * @date 2022-05-21
 */
@Data
@ApiModel("工单消息通知视图对象")
@ExcelIgnoreUnannotated
public class WorkOrderNoticeVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 工单ID
     */
    @ExcelProperty(value = "工单ID")
    @ApiModelProperty("工单ID")
    private Long workOrderId;

    /**
     * 工单名称
     */
    @ExcelProperty(value = "工单名称")
    @ApiModelProperty("工单名称")
    private String workOrderName;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 是否已读
     */
    @ExcelProperty(value = "是否已读")
    @ApiModelProperty("是否已读")
    private Integer isRead;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 工单类型
     */
    @ExcelProperty(value = "工单类型")
    @ApiModelProperty("工单类型")
    private String workOrderType;

}
