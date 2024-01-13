package com.flyhigh.api.dto.messageInfo;

import com.flyhigh.common.enums.MessageTemplateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * @ClassName BizMessageInfoUpdateDTO
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 17:01
 * @Version 1.0
 */
@Data
@ToString(callSuper = true)
public class BizMessageInfoQueryDTO {

    @ApiModelProperty(value = "模块", required = true)
    private MessageTemplateEnum messageTemplate;

    /**
     * 业务ids
     */
    private Set<Long> businessIds;

}
