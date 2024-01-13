package com.flyhigh.api.dto.home;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求服务待办事项入参
 *
 * @author Mr.Lai
 */
@ApiModel(value = "请求服务待办事项入参", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeCycleDTO {

    /** 开始时间 **/
    private String startTime;

    /** 结束时间 **/
    private String endTime;

}
