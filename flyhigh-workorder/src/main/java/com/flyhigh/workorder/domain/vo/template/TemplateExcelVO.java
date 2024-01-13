package com.flyhigh.workorder.domain.vo.template;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 模板 Excel VO
 *
 * @author flyhigh
 */
@Data
public class TemplateExcelVO {

    @ExcelProperty("模板ID")
    private Long ID;

    @ExcelProperty("模板名称")
    private String templateName;

    @ExcelProperty("模板类型")
    private String templateType;

    @ExcelProperty("模板描述")
    private String description;

    @ExcelProperty("模板图片地址 多个逗号分隔")
    private String picUrl;

    @ExcelProperty("创建人ID")
    private Long createId;

    @ExcelProperty("创建人姓名")
    private String createName;

}
