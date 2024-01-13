package com.flyhigh.api.vo.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flyhigh.common.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 租赁合同到期预警
 *
 * @author Mr.Lai
 * @date 2023/5/23
 */
@Data
public class HomeStaticsContractExpireReminderVO {

    /**
     * 租赁ID
     */
    @ApiModelProperty(name = "租赁ID")
    private Long id;

    /**
     * 合同名称
     */
    @ApiModelProperty(name = "合同名称")
    private String contractName;

    /**
     * 到期时间
     */
    @ApiModelProperty(name = "到期时间")
    @JsonFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY)
    private Date expireTime;

    /**
     * 距离天数
     */
    @ApiModelProperty(name = "距离天数")
    private Long distanceDays;

}
