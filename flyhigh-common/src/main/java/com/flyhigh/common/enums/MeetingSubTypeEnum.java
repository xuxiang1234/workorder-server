package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetingSubTypeEnum {

    /**
     * 会议附件
     */
    MEETING_DATA((short) 1),
    /**
     * 会议纪要
     */
    MEETING_MINUTES((short) 2),
    /**
     * 会议图片
     */
    MEETING_IMG((short) 3);

    private short code;
}
