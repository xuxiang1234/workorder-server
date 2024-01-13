package com.flyhigh.sms.domain.vo.pushUserAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("用户个推别名 Response VO")
@Data
@ToString(callSuper = true)
public class BizPushUserAliasRespVO {

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long id;

    @ApiModelProperty(value = "${column.columnComment}", required = true)
    private Long userId;

    @ApiModelProperty(value = "用户别名", required = true)
    private String userAlias;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @ApiModelProperty(value = "是否删除 0 否 1 是", required = true)
    private Integer delFlag;

}
