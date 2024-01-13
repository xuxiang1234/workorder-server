package com.flyhigh.api.dto.home;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "请求服务待办事项入参", description = "")
@Data
@NoArgsConstructor
public class TodoListDTO {

    /** 用户id **/
    private Long userId;

    /** 开始时间 **/
    private String startTime;

    /** 结束时间 **/
    private String endTime;

    public TodoListDTO(Long userId) {
        this.userId = userId;
    }

}
