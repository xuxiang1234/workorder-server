package com.flyhigh.api.vo.home;

import com.flyhigh.common.core.domain.PageQuery;
import com.flyhigh.common.enums.HomeCycleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 代办事项
 *
 * @author Mr.Lai
 * @date 2023/3/1
 */
@ApiModel("代办事项 Req")
@Data
public class TodoListReq extends PageQuery {

    /**
     * 代办类型
     */
    @ApiModelProperty(value = "代办类型", required = true)
    private Integer taskType;

    /**
     * 时间：DAY 当日 MONTH 本月 YEAR 本年
     */
    @ApiModelProperty(value = "时间")
    private HomeCycleEnum cycle;

}
