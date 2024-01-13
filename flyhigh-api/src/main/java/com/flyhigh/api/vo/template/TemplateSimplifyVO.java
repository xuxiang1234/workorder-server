package com.flyhigh.api.vo.template;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 模板视图对象 template
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Data
@ApiModel("模板视图对象")
@ExcelIgnoreUnannotated
public class TemplateSimplifyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "模板ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "模板ID")
    @ApiModelProperty("模板ID")
    private Long id;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    @ApiModelProperty("模板名称")
    private String templateName;

    /**
     * 模板类型
     */
    @ExcelProperty(value = "template_type")
    @ApiModelProperty("template_type")
    private String templateType;

}
