package com.flyhigh.api.vo.formWarehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "表表单仓库 Excel 导出 Request Req", description = "参数和 FormWarehousePageReq 是一致的")
@Data
public class FormWarehouseExportReq {

    @ApiModelProperty(value = "表单名称")
    private String name;

    @ApiModelProperty(value = "是否可多填表单")
    private Integer isMultForm;

    @ApiModelProperty(value = "表单内容")
    private String content;

    @ApiModelProperty(value = "创建人ID")
    private Long createId;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

}
