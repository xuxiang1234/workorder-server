package com.flyhigh.api.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("单更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormUpdateReq extends FormBaseReq {

    @ApiModelProperty(value = "主键")
    private Long id;

}
