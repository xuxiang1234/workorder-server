package com.flyhigh.api.vo.formtemplate;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 单模板 Excel VO
 *
 * @author flyhigh
 */
@Data
public class FormTemplateExcelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("关联ID")
    private Long contactId;

    @ExcelProperty("系统 保证每个系统数据不冲突")
    private String system;

    @ExcelProperty("表单内容")
    private String content;

}
