package com.flyhigh.api.vo.template;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("模板分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplatePageReq extends PageQuery {

    @ApiModelProperty(value = "模板ID")
    private Long id;

    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "模板类型")
    private String templateType;

    @ApiModelProperty(value = "模板描述")
    private String description;

    @ApiModelProperty(value = "模板图片地址 多个逗号分隔")
    private String picUrl;

    @ApiModelProperty(value = "创建人ID")
    private Long createId;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

}
