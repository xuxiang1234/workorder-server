package com.flyhigh.workorder.domain.vo.templateprocess;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 模板流程 Excel VO
 *
 * @author flyhigh
 */
@Data
public class TemplateProcessExcelVO {

    @ExcelProperty("模板ID")
    private Long templateId;

    @ExcelProperty("流程类型: 人员 组 系统")
    private String processType;

    @ExcelProperty("流程位置 从1开始")
    private Integer processIndex;

    @ExcelProperty("组ID")
    private Long groupId;

    @ExcelProperty("指定人ID")
    private Long userId;

    @ExcelProperty("指定人名称")
    private String userName;

    @ExcelProperty("指定别名")
    private String aliasName;

}
