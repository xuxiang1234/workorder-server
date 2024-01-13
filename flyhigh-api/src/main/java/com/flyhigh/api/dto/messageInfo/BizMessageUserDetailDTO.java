package com.flyhigh.api.dto.messageInfo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.enums.MessageTemplateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 消息接收人视图对象 biz_message_user_detail
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Data
@ApiModel("消息接收人视图对象API")
@ExcelIgnoreUnannotated
public class BizMessageUserDetailDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("消息id-调已读接口时传这个id")
    private Long id;

    /**
     * 消息id
     */
    @ExcelProperty(value = "消息id")
    @ApiModelProperty("消息id")
    private Long messageId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("消息发送者")
    private String messageSender;

    @ApiModelProperty("消息类型")
    private String messageType;

    @ApiModelProperty("消息类型中文含义")
    private String messageTypeDesc;

    @ApiModelProperty("消息内容")
    private String content;

    /**
     * 状态 0未读 1已读
     */
    @ExcelProperty(value = " 状态 0未读 1已读")
    @ApiModelProperty(" 状态 0未读 1已读")
    private Integer receiveStatus;

    /**
     * 是否处理审批 0 否 1是
     */
    @ExcelProperty(value = "是否处理审批 0 否 1是")
    @ApiModelProperty("是否处理审批 0 否 1是")
    private Integer whetherApprove;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    public String getMessageTypeDesc() {
        return MessageTemplateEnum.getMessageType(this.messageType);
    }

}
