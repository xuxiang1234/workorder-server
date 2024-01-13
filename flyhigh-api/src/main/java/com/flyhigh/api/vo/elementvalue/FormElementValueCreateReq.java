package com.flyhigh.api.vo.elementvalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("表单元素值新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormElementValueCreateReq extends FormElementValueBaseReq {

    @ApiModelProperty(value = "主键")
    private Long id;

}
