package com.flyhigh.api.vo.form;

import com.flyhigh.api.enums.SystemIdentification;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 单 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormBaseReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表单模板关联ID", required = true)
    @NotNull(message = "表单模板关联ID不能为空")
    private Long formTemplateContactId;

    @ApiModelProperty(value = "关联ID", required = true)
    @NotNull(message = "关联ID不能为空")
    private Long contactId;

    @ApiModelProperty(value = "系统 保证每个系统数据不冲突", required = true)
    @NotNull(message = "系统 保证每个系统数据不冲突不能为空")
    private SystemIdentification system;

}



