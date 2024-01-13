package com.flyhigh.api.vo.formtemplate;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ApiModel("单模板更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormTemplateUpdateReq extends FormTemplateBaseReq {

}
