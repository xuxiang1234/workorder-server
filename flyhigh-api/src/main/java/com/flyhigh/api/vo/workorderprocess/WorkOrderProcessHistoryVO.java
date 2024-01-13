package com.flyhigh.api.vo.workorderprocess;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 工单流程视图对象 work_order_process
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Data
@ApiModel("工单流程视图对象")
@ExcelIgnoreUnannotated
public class WorkOrderProcessHistoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 流程位置 从1开始
     */
    @ExcelProperty(value = "流程位置 从1开始")
    @ApiModelProperty("流程位置 从1开始")
    private Integer processIndex;

    /**
     * 工单流程状态 驳回 确认
     */
    @ExcelProperty(value = "工单流程状态 驳回 确认 指定 创建 待处理 结束")
    @ApiModelProperty("工单流程状态 驳回 确认 指定 创建 待处理 结束")
    private String status;

    /**
     * 评价
     */
    @ExcelProperty(value = "评价")
    @ApiModelProperty("评价")
    private String evaluate;

    /**
     * 评价图片
     */
    @ExcelProperty(value = "评价图片")
    @ApiModelProperty("评价图片")
    private String evaluatePicUrl;

    /**
     * 流程位置 从1开始
     */
    @ExcelProperty(value = "是否新增")
    @ApiModelProperty("是否新增")
    private Integer isNew;

    /**
     * 是否最后一个流程
     */
    @ExcelProperty(value = "是否最后一个流程")
    @ApiModelProperty("是否最后一个流程")
    private Integer isLast;

    /**
     * 是否是调度员处理
     */
    @ExcelProperty(value = "是否是调度员处理")
    @ApiModelProperty("是否是调度员处理")
    private Boolean isDispatcherUser;

    /**
     * 指定人ID
     */
    @ExcelProperty(value = "指定人ID")
    @ApiModelProperty("指定人ID")
    private Long userId;

    /**
     * 指定人名称
     */
    @ExcelProperty(value = "指定人名称")
    @ApiModelProperty("指定人名称")
    private String userName;

    /**
     * 指定别名
     */
    @ExcelProperty(value = "指定别名")
    @ApiModelProperty("指定别名")
    private String aliasName;

    /**
     * 指定人名称
     */
    @ExcelProperty(value = "指定人名称")
    @ApiModelProperty("指定人名称")
    private String nextUserName;

    /**
     * 指定别名
     */
    @ExcelProperty(value = "指定别名")
    @ApiModelProperty("指定别名")
    private String nextAliasName;

    /**
     * 是否存在表单
     */
    @ExcelProperty(value = "是否存在表单")
    @ApiModelProperty("是否存在表单")
    private int isExistForm;

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


}
