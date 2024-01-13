package com.flyhigh.sms.domain.vo.pushUserAlias;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.flyhigh.common.utils.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 用户个推别名 Base Req，提供给添加、修改、详细的子 req 使用
 * 如果子 Req 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BizPushUserAliasBaseReq {

    @ApiModelProperty(value = "${column.columnComment}")
    private Long userId;

    @ApiModelProperty(value = "用户别名")
    private String userAlias;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}



