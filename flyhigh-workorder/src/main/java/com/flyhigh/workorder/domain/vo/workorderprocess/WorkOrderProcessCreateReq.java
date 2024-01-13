package com.flyhigh.workorder.domain.vo.workorderprocess;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("工单流程新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderProcessCreateReq extends WorkOrderProcessBaseReq {


}
