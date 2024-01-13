package com.flyhigh.workorder.domain.vo.template;

import com.flyhigh.workorder.domain.vo.templateprocess.TemplateProcessCreateReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel("模板新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateCreateReq extends TemplateBaseReq {

    @ApiModelProperty(value = "模板流程", required = true)
    @NotEmpty(message = "模板流程不能为空")
    @Size(min = 3, message = "模板流程不能少于3个")
    private List<TemplateProcessCreateReq> templateProcessReqs;

}
