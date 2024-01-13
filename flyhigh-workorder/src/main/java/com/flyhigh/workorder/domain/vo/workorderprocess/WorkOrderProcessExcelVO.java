package com.flyhigh.workorder.domain.vo.workorderprocess;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 工单流程 Excel VO
 *
 * @author flyhigh
 */
@Data
public class WorkOrderProcessExcelVO {

    @ExcelProperty("工单ID")
    private Long workOrderId;

    @ExcelProperty("流程类型: 人员 组 系统")
    private String processType;

    @ExcelProperty("流程位置 从1开始")
    private Integer processIndex;

    @ExcelProperty("工单流程状态 驳回 确认")
    private String status;

    @ExcelProperty("评价")
    private String evaluate;

    @ExcelProperty("评价图片")
    private String evaluatePicUrl;

    @ExcelProperty("组ID")
    private Long groupId;

    @ExcelProperty("指定人ID")
    private Long userId;

    @ExcelProperty("指定人名称")
    private String userName;

    @ExcelProperty("指定别名")
    private String aliasName;

}
