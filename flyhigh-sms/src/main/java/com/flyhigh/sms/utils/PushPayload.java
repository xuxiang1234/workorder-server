package com.flyhigh.sms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PushPayload
 * @Description
 * @Author huangjinhui
 * @Date 2022/5/31 16:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushPayload {

    /**
     * 消息参数
     **/
    private String params;

    /**
     * 消息类型
     **/
    private String MessageTemplateValue;

}
