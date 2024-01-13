package com.flyhigh.workorder.domain.vo.workorder;

import com.flyhigh.api.vo.element.FormElementCreateReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("工单处理接口 Request")
@Data
@ToString(callSuper = true)
public class WorkOrderHandleReq {


    @ApiModelProperty(value = "处理状态 AGREE: 确认  REJECT: 驳回  ADD: 指定", required = true)
    @NotNull(message = "处理状态不能为空")
    private ProcessHandleStatusReq status;


    @ApiModelProperty(value = "工单ID", required = true)
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;


    @ApiModelProperty(value = "评价", required = true)
    @NotBlank(message = "评价不能为空")
    private String evaluate;


    @ApiModelProperty(value = "评价图片")
    private String evaluatePicUrl;


    @ApiModelProperty(value = "流程类型: 人员 组 系统")
    private String processType;


    @ApiModelProperty(value = "组ID")
    private Long groupId;


    @ApiModelProperty(value = "指定人ID")
    private Long userId;


    @ApiModelProperty(value = "指定人名称")
    private String userName;


    @ApiModelProperty(value = "指定别名")
    private String aliasName;


    @ApiModelProperty(value = "指定下个人的表单Id-来源表单仓库")
    private Long nextFormId;


    @ApiModelProperty(value = "表单内容")
    List<List<FormElementCreateReq>> formElementContentReqs;

}
