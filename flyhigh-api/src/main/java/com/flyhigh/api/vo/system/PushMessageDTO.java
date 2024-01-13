package com.flyhigh.api.vo.system;

import com.flyhigh.api.enums.MessageTypeEnum;
import com.flyhigh.api.enums.PlatformEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息推送入参
 */
@Data
public class PushMessageDTO implements Serializable {

    /**
     * 接收人id
     */
    private Long userId;

    /**
     * 消息体
     */
    private String messageBody;

    /**
     * 平台来源
     */
    private PlatformEnum platform;

    /**
     * 链接地址（如存在前端赢处理点击链接）
     */
    private String link;

    /**
     * 消息类型
     */
    private MessageTypeEnum messageTypeEnum;

}
