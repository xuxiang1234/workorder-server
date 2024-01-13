package com.flyhigh.sms.utils;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName PushPayloadTransmission
 * @Description
 * @Author huangjinhui
 * @Date 2022/5/31 16:30
 * @Version 1.0
 */
@Data
@Builder
public class PushTransmission {

    /**
     * 标题
     **/
    private String title;

    /**
     * 内容
     **/
    private String content;

    /**
     * 自定义数据
     **/
    private PushPayload payload;

}
