package com.flyhigh.api.vo.formWarehouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 表表单仓库 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormWarehouseBaseReq {

    /**
     * 表单名称
     */
    @ApiModelProperty(value = "表单名称", required = true)
    @NotBlank(message = "表单名称不能为空")
    private String name;

    /**
     * 是否可多填表单
     */
    @ApiModelProperty(value = "是否可多填表单", required = true)
    @NotNull(message = "是否可多填表单")
    private Integer isMultForm;

    /**
     * 表单内容
     */
    @ApiModelProperty(value = "表单内容", required = true)
    @NotBlank(message = "表单内容不能为空")
    private String content;

    /**
     * 关联ID
     */
    @ApiModelProperty(value = "关联ID")
    private Long contactId;

    /**
     * 系统
     */
    @ApiModelProperty(value = "系统")
    private String system;

    /**
     * 类别
     */
    @ApiModelProperty("类别")
    private String category;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}



