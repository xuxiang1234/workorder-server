package com.flyhigh.api.vo.templateprocess;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 模板流程视图对象 template_process
 *
 * @author flyhigh
 * @date 2022-05-15
 */
@Data
@ApiModel("模板流程视图对象")
@ExcelIgnoreUnannotated
public class TemplateProcessVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程ID
     */
    @ExcelProperty(value = "流程ID")
    @ApiModelProperty("流程ID")
    private Long id;

    /**
     * 模板ID
     */
    @ExcelProperty(value = "模板ID")
    @ApiModelProperty("模板ID")
    private Long templateId;

    /**
     * 流程类型: 人员 组 系统
     */
    @ExcelProperty(value = "流程类型: 人员 组 系统")
    @ApiModelProperty("流程类型: 人员 组 系统")
    private String processType;

    /**
     * 流程位置 从1开始
     */
    @ExcelProperty(value = "流程位置 从1开始")
    @ApiModelProperty("流程位置 从1开始")
    private Integer processIndex;

    /**
     * 组ID
     */
    @ExcelProperty(value = "组ID")
    @ApiModelProperty("组ID")
    private Long groupId;

    /**
     * 指定人ID
     */
    @ExcelProperty(value = "指定人ID")
    @ApiModelProperty("指定人ID")
    private Long userId;

    /**
     * 指定人名称
     */
    @ExcelProperty(value = "指定人名称")
    @ApiModelProperty("指定人名称")
    private String userName;

    /**
     * 指定别名
     */
    @ExcelProperty(value = "指定别名")
    @ApiModelProperty("指定别名")
    private String aliasName;

    /**
     * 是否最后一个流程
     */
    @ExcelProperty(value = "是否最后一个流程")
    @ApiModelProperty("是否最后一个流程")
    private Integer isLast;

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
     * 表单内容
     */
    @ExcelProperty(value = "表单内容")
    @ApiModelProperty("表单内容")
    private String formTemplateContent;


    /**
     * 是否可多填表单
     */
    @ExcelProperty(value = "是否可多填表单")
    @ApiModelProperty("是否可多填表单")
    private Integer isMultForm;


}
