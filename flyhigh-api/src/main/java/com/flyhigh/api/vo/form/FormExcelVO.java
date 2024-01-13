package com.flyhigh.api.vo.form;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 单 Excel VO
 *
 * @author flyhigh
 */
@Data
public class FormExcelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("关联ID")
    private Long contactId;

    @ExcelProperty("系统 保证每个系统数据不冲突")
    private String system;

    @ExcelProperty("表单内容")
    private String content;

}
