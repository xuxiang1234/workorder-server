package com.flyhigh.api.vo.elementvalue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 表单元素值 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormElementValueBaseReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "元素ID", required = true)
    @NotNull(message = "元素ID不能为空")
    private Long formElementId;

    @ApiModelProperty(value = "值索引", required = true)
    @NotNull(message = "值索引不能为空")
    private Integer valueIndex;

    @ApiModelProperty(value = "元素值", required = true)
    @NotNull(message = "元素值不能为空")
    private String value;

    @ApiModelProperty(value = "是否数字", required = true)
    @NotNull(message = "是否数字不能为空")
    private Integer isNumber;

}



