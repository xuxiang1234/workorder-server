package com.flyhigh.api.vo.formWarehouse;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 表表单仓库视图对象 form_warehouse
 *
 * @author flyhigh
 * @date 2022-08-06
 */
@Data
@ApiModel("表表单仓库视图对象")
@ExcelIgnoreUnannotated
public class FormWarehouseVO {

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
    @ApiModelProperty("关联ID")
    private Long contactId;

    /**
     * 系统
     */
    @ApiModelProperty("系统")
    private String system;

    /**
     * 类别
     */
    @ApiModelProperty("类别")
    private String category;

    /**
     * 表单名称
     */
    @ExcelProperty(value = "表单名称")
    @ApiModelProperty("表单名称")
    private String name;

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
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty("备注")
    private String remark;

}
