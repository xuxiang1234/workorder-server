package com.flyhigh.api.vo.workorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("工单新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderCreateReq extends WorkOrderBaseReq {


    @ApiModelProperty(value = "模板编号", required = true)
    @NotNull(message = "模板编号不能为空")
    private Long templateId;

}
