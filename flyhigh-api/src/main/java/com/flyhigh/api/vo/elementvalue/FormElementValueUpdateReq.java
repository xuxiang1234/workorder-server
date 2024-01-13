package com.flyhigh.api.vo.elementvalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("表单元素值更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormElementValueUpdateReq extends FormElementValueBaseReq {

    @ApiModelProperty(value = "主键")
    private Long id;

}
