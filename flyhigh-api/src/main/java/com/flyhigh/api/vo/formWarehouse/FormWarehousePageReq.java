package com.flyhigh.api.vo.formWarehouse;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 表表单仓库分页
 */
@ApiModel("表表单仓库分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormWarehousePageReq extends PageQuery {

    /**
     * 关联ID
     */
    @ApiModelProperty(value = "关联ID")
    private Long contactId;

    /**
     * 系统
     */
    @ApiModelProperty(value = "系统")
    private String system;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别")
    private String category;

    /**
     * 表单名称
     */
    @ApiModelProperty(value = "表单名称")
    private String name;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    private String createName;

}
