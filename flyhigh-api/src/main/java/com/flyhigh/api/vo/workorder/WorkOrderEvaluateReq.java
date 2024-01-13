package com.flyhigh.api.vo.workorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 工单 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WorkOrderEvaluateReq implements Serializable {

    private static final long serialVersionUID = -6921156046644410618L;

    @ApiModelProperty(value = "工单ID", required = true)
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;


    @ApiModelProperty(value = "评价", required = true)
    @NotBlank(message = "评价不能为空")
    private String evaluate;


    @ApiModelProperty(value = "评分", required = true)
    @NotNull(message = "评分不能为空")
    private Integer score;


}



