package com.flyhigh.api.vo.form;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.api.vo.element.FormElementVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 单视图对象 form
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@ApiModel("单视图对象")
@ExcelIgnoreUnannotated
public class FormVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 关联ID
     */
    @ExcelProperty(value = "关联ID")
    @ApiModelProperty("关联ID")
    private Long contactId;

    /**
     * 系统 保证每个系统数据不冲突
     */
    @ExcelProperty(value = "系统 保证每个系统数据不冲突")
    @ApiModelProperty("系统 保证每个系统数据不冲突")
    private String system;

    /**
     * 是否可多填表单
     */
    @ExcelProperty(value = "是否可多填表单")
    @ApiModelProperty("是否可多填表单")
    private Integer isMultForm;

    /**
     * 表单内容
     */
    @ExcelProperty(value = "表单内容")
    @ApiModelProperty("表单内容")
    private String content;

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
     * 元素
     */
    @ExcelProperty(value = "元素")
    @ApiModelProperty("元素")
    List<List<FormElementVO>> formElementVOList;

}
