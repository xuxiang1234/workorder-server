package com.flyhigh.api.vo.element;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ApiModel("表单元素 Response VO")
@Data
@ToString(callSuper = true)
public class FormElementRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "表单ID", required = true)
    private Long formId;

    @ApiModelProperty(value = "元素CODE 同一表单CODE唯一", required = true)
    private String code;

    @ApiModelProperty(value = "元素标题", required = true)
    private String label;

    @ApiModelProperty(value = "元素类型", required = true)
    private String type;

    @ApiModelProperty(value = "业务名称")
    private String bizName;

    @ApiModelProperty(value = "业务字段名")
    private String bizField;

    @ApiModelProperty(value = "业务字段分组key")
    private String bizKey;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
