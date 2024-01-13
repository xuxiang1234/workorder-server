package com.flyhigh.workorder.domain.vo.workorderprocess;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("工单流程更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderProcessUpdateReq extends WorkOrderProcessBaseReq {


}
