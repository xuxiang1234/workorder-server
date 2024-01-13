package com.flyhigh.api.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ApiModel("单 Response VO")
@Data
@ToString(callSuper = true)
public class FormRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "关联ID", required = true)
    private Long contactId;

    @ApiModelProperty(value = "系统 保证每个系统数据不冲突", required = true)
    private String system;

    @ApiModelProperty(value = "表单内容", required = true)
    private String content;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
