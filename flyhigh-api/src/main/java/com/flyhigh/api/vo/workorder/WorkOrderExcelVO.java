package com.flyhigh.api.vo.workorder;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 运维工单 Excel VO
 *
 * @author flyhigh
 */
@Data
public class WorkOrderExcelVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ExcelProperty("工单ID")
    private Long id;

    @ExcelProperty("工单名称")
    private String workOrderName;

    @ExcelProperty("工单类型")
    private String workOrderType;

    @ExcelProperty("工单描述")
    private String description;

    @ExcelProperty("报价")
    private String offer;

    @ExcelProperty("创建人姓名")
    private String createName;

    @ExcelProperty("完成时间")
    @ColumnWidth(18)
    private Date completeTime;

}
