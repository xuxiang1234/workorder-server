package com.flyhigh.sms.domain.vo.messageUserDetail;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
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
@ApiModel("消息接收人视图对象")
@ExcelIgnoreUnannotated
public class BizMessageUserDetailVO {

    private static final long serialVersionUID = 1L;

    /**
     * 消息id-调已读接口时传这个id
     */
    @ApiModelProperty("消息id-调已读接口时传这个id")
    private Long id;

    /**
     * 消息id
     */
    @ApiModelProperty("消息id")
    private Long messageId;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 消息发送者
     */
    @ApiModelProperty("消息发送者")
    private String messageSender;

    /**
     * 消息类型
     */
    @ApiModelProperty("消息类型")
    private String messageType;

    /**
     * 消息分类
     */
    @ApiModelProperty("消息分类")
    private String messageClassify;

    /**
     * 消息类型中文含义
     */
    @ApiModelProperty("消息类型中文含义")
    private String messageTypeDesc;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String content;

    /**
     * 状态 0未读 1已读
     */
    @ApiModelProperty(" 状态 0未读 1已读")
    private Integer receiveStatus;

    /**
     * 是否处理审批 0 否 1是
     */
    @ApiModelProperty("是否处理审批 0 否 1是")
    private Integer whetherApprove;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    public String getMessageTypeDesc() {
        return MessageTemplateEnum.getMessageType(this.messageType);
    }

}
