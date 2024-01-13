package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息接收状态
 *
 * @ClassName MessageReceiveStatusEnum
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 13:56
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MessageReceiveStatusEnum {
    /**
     * 未读
     */
    Unread(0),
    /**
     * 已读
     */
    Read(1);

    private Integer code;
}
