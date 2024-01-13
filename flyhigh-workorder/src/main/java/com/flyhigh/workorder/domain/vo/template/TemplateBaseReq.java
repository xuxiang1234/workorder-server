package com.flyhigh.workorder.domain.vo.template;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 模板 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TemplateBaseReq {


    @ApiModelProperty(value = "模板名称", required = true)
    @NotBlank(message = "模板名称不能为空")
    private String templateName;


    @ApiModelProperty(value = "模板类型", required = true)
    @NotBlank(message = "模板类型不能为空")
    private String templateType;


    @ApiModelProperty(value = "模板描述")
    private String description;


    @ApiModelProperty(value = "模板图片地址 多个逗号分隔")
    private String picUrl;

}



