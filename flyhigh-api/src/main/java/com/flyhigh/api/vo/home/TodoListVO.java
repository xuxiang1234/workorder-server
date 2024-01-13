package com.flyhigh.api.vo.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flyhigh.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 代办事项列表
 *
 * @author Mr.Lai
 * @date 2023/2/7
 */
@ApiModel("代办事项列表 VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TodoListVO {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    private String desc;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    /**
     * 提出日期
     */
    @ApiModelProperty(value = "提出日期", required = true)
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date date;

    /**
     * 代办类型
     * 1:待缴费提醒
     * 2:工单提醒
     * 3:合同到期提醒
     * 4:开票提醒
     */
    @ApiModelProperty(value = "代办类型", required = true)
    private Integer type;

}
