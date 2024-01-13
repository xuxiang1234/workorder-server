package com.flyhigh.workorder.domain.vo.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "模板 Excel 导出 Request Req", description = "参数和 templatePageReq 是一致的")
@Data
public class TemplateExportReq {

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
