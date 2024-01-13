package com.flyhigh.api.vo.element;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单元素 Excel VO
 *
 * @author flyhigh
 */
@Data
public class FormElementExcelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("表单ID")
    private Long formId;

    @ExcelProperty("元素CODE 同一表单CODE唯一")
    private String code;

    @ExcelProperty("元素标题")
    private String label;

    @ExcelProperty("元素类型")
    private String type;

    @ExcelProperty("业务名称")
    private String bizName;

    @ExcelProperty("业务字段名")
    private String bizField;

    @ExcelProperty("业务字段分组key")
    private String bizKey;

}
