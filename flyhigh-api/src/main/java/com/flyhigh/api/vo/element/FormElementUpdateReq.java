package com.flyhigh.api.vo.element;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("表单元素更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormElementUpdateReq extends FormElementBaseReq {

    @ApiModelProperty(value = "主键")
    private Long id;

}
