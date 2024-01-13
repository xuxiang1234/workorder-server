package com.flyhigh.api.vo.elementvalue;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单元素值 Excel VO
 *
 * @author flyhigh
 */
@Data
public class FormElementValueExcelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("元素ID")
    private Long formElementId;

    @ExcelProperty("值索引")
    private Integer valueIndex;

    @ExcelProperty("元素值")
    private String value;

    @ExcelProperty("是否数字")
    private Integer isNumber;

}
