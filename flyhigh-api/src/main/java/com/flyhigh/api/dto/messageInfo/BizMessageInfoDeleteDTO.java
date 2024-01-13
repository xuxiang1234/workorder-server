package com.flyhigh.api.dto.messageInfo;

import com.flyhigh.common.enums.MessageTemplateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @ClassName BizMessageInfoCreateDTO
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 15:29
 * @Version 1.0
 */
@Data
@ToString(callSuper = true)
public class BizMessageInfoDeleteDTO {

    @ApiModelProperty(value = "模块", required = true)
    private MessageTemplateEnum messageTemplate;

    @ApiModelProperty(value = "业务主键id", required = true)
    @NotNull(message = "业务主键id不能为空")
    private Long businessId;

}
