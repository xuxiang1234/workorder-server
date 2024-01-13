package com.flyhigh.api.vo.template;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.api.vo.templateprocess.TemplateProcessVO;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 模板视图对象 template
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Data
@ApiModel("模板视图对象")
@ExcelIgnoreUnannotated
public class TemplateVO implements Serializable {

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
    @ExcelProperty(value = "模板类型")
    @ApiModelProperty("模板类型")
    private String templateType;

    /**
     * 模板描述
     */
    @ExcelProperty(value = "模板描述")
    @ApiModelProperty("模板描述")
    private String description;

    /**
     * 模板图片地址 多个逗号分隔
     */
    @ExcelProperty(value = "模板图片地址 多个逗号分隔")
    @ApiModelProperty("模板图片地址 多个逗号分隔")
    private String picUrl;

    /**
     * 创建人ID
     */
    @ExcelProperty(value = "创建人ID")
    @ApiModelProperty("创建人ID")
    private Long createId;

    /**
     * 创建人姓名
     */
    @ExcelProperty(value = "创建人姓名")
    @ApiModelProperty("创建人姓名")
    private String createName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 模板流程
     */
    @ExcelProperty(value = "模板流程")
    @ApiModelProperty("模板流程")
    private List<TemplateProcessVO> templateProcessVOList;

}
