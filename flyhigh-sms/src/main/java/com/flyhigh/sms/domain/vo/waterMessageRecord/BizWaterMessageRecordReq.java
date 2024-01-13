package com.flyhigh.sms.domain.vo.waterMessageRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("业务消息流水分页 Request Req")
@Data
@ToString(callSuper = true)
public class BizWaterMessageRecordReq {

    @ApiModelProperty(value = "类型 1 日常工作 2 教育基地 3 发文")
    private Integer type;

    @ApiModelProperty(value = "前N条")
    private Long topN;

    @ApiModelProperty(value = "部门id", required = true)
    private Long deptId;

}
