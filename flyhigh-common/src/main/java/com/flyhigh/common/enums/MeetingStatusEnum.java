package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingStatusEnum {

    /**
     * 未开始状态
     */
    INIT(0),

    /**
     * 进行中
     */
    ING(5),

    /**
     * 已结束
     */
    FINISH(1);

    private int code;

}
