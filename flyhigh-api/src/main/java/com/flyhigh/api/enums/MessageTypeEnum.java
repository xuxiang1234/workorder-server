package com.flyhigh.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息；类型
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    DOWNLOAD_SUCCESS_MESSAGE("download", "下载成功通知"),

    SYSTEM_NOTICE("systemNotice", "系统通知"),

    PING("ping", "前端心跳检测"),

    PONG("pong", "后端心跳返回"),

    ONCONNECTING("connecting", "连接成功"),
    ;

    private String type;
    private String desc;


}
