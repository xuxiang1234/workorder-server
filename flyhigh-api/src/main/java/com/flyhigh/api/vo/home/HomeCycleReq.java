package com.flyhigh.api.vo.home;

import com.flyhigh.common.enums.HomeCycleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Mr.Lai
 * @date 2023/4/11
 */
@Data
public class HomeCycleReq {

    /**
     * 时间：MONTH 本月 SEASON 本季 YEAR 本年
     */
    @ApiModelProperty(value = "时间")
    private HomeCycleEnum cycle;

}
