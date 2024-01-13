package com.flyhigh.api.vo.elementvalue;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 表单元素值视图对象 form_element_value
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@ApiModel("表单元素值视图对象")
@ExcelIgnoreUnannotated
public class FormElementValueVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 元素ID
     */
    @ExcelProperty(value = "元素ID")
    @ApiModelProperty("元素ID")
    private Long formElementId;

    /**
     * 值索引
     */
    @ExcelProperty(value = "值索引")
    @ApiModelProperty("值索引")
    private Integer valueIndex;

    /**
     * 元素值
     */
    @ExcelProperty(value = "元素值")
    @ApiModelProperty("元素值")
    private String value;

    /**
     * 是否数字
     */
    @ExcelProperty(value = "是否数字")
    @ApiModelProperty("是否数字")
    private Integer isNumber;

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

}
