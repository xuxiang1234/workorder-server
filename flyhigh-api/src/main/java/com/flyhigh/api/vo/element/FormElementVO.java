package com.flyhigh.api.vo.element;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 表单元素视图对象 form_element
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@ApiModel("表单元素视图对象")
@ExcelIgnoreUnannotated
public class FormElementVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表单ID
     */
    @ExcelProperty(value = "表单ID")
    @ApiModelProperty("表单ID")
    private Long formId;

    /**
     * 元素CODE 同一表单CODE唯一
     */
    @ExcelProperty(value = "元素CODE 同一表单CODE唯一")
    @ApiModelProperty("元素CODE 同一表单CODE唯一")
    private String code;

    /**
     * 元素标题
     */
    @ExcelProperty(value = "元素标题")
    @ApiModelProperty("元素标题")
    private String label;

    /**
     * 元素类型
     */
    @ExcelProperty(value = "元素类型")
    @ApiModelProperty("元素类型")
    private String type;

    /**
     * 业务名称
     */
    @ExcelProperty("业务名称")
    @ApiModelProperty("业务名称")
    private String bizName;

    /**
     * 业务字段名
     */
    @ExcelProperty("业务字段名")
    @ApiModelProperty("业务字段名")
    private String bizField;

    /**
     * 业务字段分组key
     */
    @ExcelProperty("业务字段分组key")
    @ApiModelProperty("业务字段分组key")
    private String bizKey;

    /**
     * 表单索引
     */
    @ExcelProperty(value = "表单索引")
    @ApiModelProperty("表单索引")
    private Integer formIndex;

    /**
     * 元素值
     */
    @ExcelProperty(value = "元素值")
    @ApiModelProperty("元素值")
    private List<String> values;

    /**
     * 元素值字符串
     */
    @ExcelProperty(value = "元素值字符串")
    @ApiModelProperty("元素值字符串")
    private String valueString;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
