package com.flyhigh.api.vo.formWarehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("表表单仓库 Response VO")
@Data
@ToString(callSuper = true)
public class FormWarehouseRespVO {

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "表单名称", required = true)
    private String name;

    @ApiModelProperty(value = "是否可多填表单", required = true)
    private Integer isMultForm;

    @ApiModelProperty(value = "表单内容", required = true)
    private String content;

    @ApiModelProperty(value = "创建人ID", required = true)
    private Long createId;

    @ApiModelProperty(value = "创建人姓名", required = true)
    private String createName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
