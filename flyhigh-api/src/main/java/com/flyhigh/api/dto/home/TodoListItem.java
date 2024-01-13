package com.flyhigh.api.dto.home;

import com.flyhigh.common.enums.TaskRemindersModuleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "待办事项详情", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListItem {

    @ApiModelProperty(value = "日期", required = true)
    private Date taskTime;

    @ApiModelProperty(value = "对应的业务主键id", required = true)
    private Long taskId;

    @ApiModelProperty(value = "任务类型", required = true)
    private TaskRemindersModuleEnum taskType;

    @ApiModelProperty(value = "任务内容", required = true)
    private String taskContent;

    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @ApiModelProperty(value = "任务描述", required = true)
    private String taskDesc;

}
