package com.flyhigh.api.vo.formWarehouse;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 表表单仓库 Excel VO
 *
 * @author flyhigh
 */
@Data
public class FormWarehouseExcelVO {

    @ExcelProperty("表单名称")
    private String name;

    @ExcelProperty("是否可多填表单")
    private Integer isMultForm;

    @ExcelProperty("表单内容")
    private String content;

    @ExcelProperty("创建人ID")
    private Long createId;

    @ExcelProperty("创建人姓名")
    private String createName;

}
