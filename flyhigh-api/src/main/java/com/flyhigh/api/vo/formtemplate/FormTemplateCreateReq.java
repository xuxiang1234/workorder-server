package com.flyhigh.api.vo.formtemplate;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("单模板新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormTemplateCreateReq extends FormTemplateBaseReq {

}
