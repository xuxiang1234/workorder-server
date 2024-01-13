package com.flyhigh.sms.domain.vo.messageUserDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel("消息接收人更新 Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMessageUserDetailUpdateReq extends BizMessageUserDetailBaseReq {

    /**
     * 消息ID列表
     */
    @ApiModelProperty(value = "消息ID列表", required = true)
    @NotEmpty(message = "ID不能为空")
    private List<Long> ids;

}
