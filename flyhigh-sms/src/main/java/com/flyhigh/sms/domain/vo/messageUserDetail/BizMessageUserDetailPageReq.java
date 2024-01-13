package com.flyhigh.sms.domain.vo.messageUserDetail;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 消息接收人分页
 */
@ApiModel("消息接收人分页 Request Req")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMessageUserDetailPageReq extends PageQuery {

    /**
     * 状态 空-全部 0-未读 1-已读
     */
    @ApiModelProperty(value = "状态 空-全部 0-未读 1-已读")
    private Integer receiveStatus;

}
