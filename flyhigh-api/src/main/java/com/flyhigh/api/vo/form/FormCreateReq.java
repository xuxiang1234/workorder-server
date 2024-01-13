package com.flyhigh.api.vo.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("单新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormCreateReq extends FormBaseReq {

}
