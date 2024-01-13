package com.flyhigh.workorder.domain.vo.workordernotice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ApiModel("工单消息通知更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderNoticeUpdateReq extends WorkOrderNoticeBaseReq {

    @ApiModelProperty(value = "主键")
    private Long id;


}
