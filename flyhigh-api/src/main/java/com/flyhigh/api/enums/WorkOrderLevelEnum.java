package com.flyhigh.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 工单等级枚举
 *
 * @author Mr.Lai
 */
@AllArgsConstructor
@Getter
public enum WorkOrderLevelEnum {

    /**s
     * 低
     */
    LOW("低"),

    /**
     * 中
     */
    MEDIUM("中"),

    /**
     * 高
     */
    HIGH( "高"),

    /**
     * 紧急
     */
    URGENT( "紧急"),

    ;

    /**
     * 类型
     */
    private final String type;

}
