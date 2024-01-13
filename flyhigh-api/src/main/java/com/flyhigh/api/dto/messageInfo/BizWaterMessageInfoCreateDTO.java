package com.flyhigh.api.dto.messageInfo;

import com.flyhigh.common.enums.WaterMessageTemplateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BizMessageInfoCreateDTO
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 15:29
 * @Version 1.0
 */
@Data
@ToString(callSuper = true)
public class BizWaterMessageInfoCreateDTO {

    @ApiModelProperty(value = "模块", required = true)
    private WaterMessageTemplateEnum waterMessageTemplateEnum;

    @ApiModelProperty(value = "业务主键id", required = true)
    @NotNull(message = "业务主键id不能为空")
    private Long businessId;

    @ApiModelProperty(value = "自定义业务参数")
    private String customParameters = "";

    @ApiModelProperty(value = "模板变量参数", required = true)
    @NotNull(message = "模板变量参数不能为空")
    private Map<String, String> templateParam;

    /**
     * 事件参与人
     */
    private List<String> userNames;

    /**
     * 用户集合列表
     */
    private List<Long> userIds;

    /**
     * 消息发送者 默认 系统消息
     */
    private Long deptId;

}
