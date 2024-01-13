package com.flyhigh.workorder.domain.enums;

public enum ProcessStatus {

    /**
     * 确认
     */
    AGREE,


    /**
     * 驳回
     */
    REJECT,


    /**
     * 指定人员
     */
    ADD,


    /**
     * 处理中 代表当前扭转到的流程
     */
    NOW,
}
