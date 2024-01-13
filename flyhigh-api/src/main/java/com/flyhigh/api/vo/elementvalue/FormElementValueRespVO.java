package com.flyhigh.api.vo.elementvalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ApiModel("表单元素值 Response VO")
@Data
@ToString(callSuper = true)
public class FormElementValueRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "元素ID", required = true)
    private Long formElementId;

    @ApiModelProperty(value = "值索引", required = true)
    private Integer valueIndex;

    @ApiModelProperty(value = "元素值", required = true)
    private String value;

    @ApiModelProperty(value = "是否数字", required = true)
    private Integer isNumber;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
