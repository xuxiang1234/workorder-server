package com.flyhigh.workorder.domain.vo.workorderprocess;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 工单流程 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WorkOrderProcessBaseReq {


    @ApiModelProperty(value = "流程类型: 人员 组 系统", required = true)
    @NotNull(message = "流程类型: 人员 组 系统不能为空")
    private String processType;


    @ApiModelProperty(value = "流程位置 从1开始", required = true)
    @NotNull(message = "流程位置 从1开始不能为空")
    private Integer processIndex;


    @ApiModelProperty(value = "组ID")
    private Long groupId;


    @ApiModelProperty(value = "指定人ID")
    private Long userId;


    @ApiModelProperty(value = "指定人名称")
    private String userName;


    @ApiModelProperty(value = "指定别名", required = true)
    @NotNull(message = "指定别名不能为空")
    private String aliasName;


}



