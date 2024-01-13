package com.flyhigh.api.vo.element;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 表单元素 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormElementBaseReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表单ID")
    @NotNull(message = "表单ID不能为空")
    private Long formId;

    @ApiModelProperty(value = "元素CODE 同一表单CODE唯一")
    @NotBlank(message = "元素CODE 同一表单CODE唯一不能为空")
    private String code;

    @ApiModelProperty(value = "元素标题")
    @NotBlank(message = "元素标题不能为空")
    private String label;

    @ApiModelProperty(value = "元素类型")
    @NotBlank(message = "元素类型不能为空")
    private String type;

    @ApiModelProperty(value = "业务名称")
    private String bizName;

    @ApiModelProperty(value = "业务字段名")
    private String bizField;

    @ApiModelProperty(value = "业务字段分组key")
    private String bizKey;

}



