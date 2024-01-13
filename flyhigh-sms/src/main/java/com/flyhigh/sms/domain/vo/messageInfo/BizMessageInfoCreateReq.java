package com.flyhigh.sms.domain.vo.messageInfo;

import com.flyhigh.common.enums.MessageTemplateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@ApiModel("消息新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMessageInfoCreateReq extends BizMessageInfoBaseReq {

    @ApiModelProperty(value = "模块", required = true)
    private MessageTemplateEnum messageTemplate;

    @ApiModelProperty(value = "业务主键id", required = true)
    @NotNull(message = "业务主键id不能为空")
    private Long businessId;

    @ApiModelProperty(value = "自定义业务参数")
    private String customParameters;

    @ApiModelProperty(value = "模板变量参数", required = true)
    @NotNull(message = "模板变量参数不能为空")
    private Map<String, String> templateParam;

    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 接收消息人员
     */
    private List<Long> userIds;

    /**
     * 消息发送者 默认 系统消息
     */
    private String messageSender;

}
