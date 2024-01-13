package com.flyhigh.api.vo.workorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "运维工单 Excel 导出 Request Req", description = "参数和 OperationalWorkOrderPageReq 是一致的")
@Data
public class WorkOrderExportReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工单ID集合")
    private List<Long> workOrderIdList;

    @ApiModelProperty(value = "工单类型")
    private String workOrderType;

}
