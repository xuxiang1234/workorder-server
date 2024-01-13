package com.flyhigh.workorder.domain.vo.workordernotice;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("工单消息通知新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderNoticeCreateReq extends WorkOrderNoticeBaseReq {


}
