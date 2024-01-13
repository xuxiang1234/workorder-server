package com.flyhigh.common.enums;

import java.util.stream.Stream;

/**
 * 时间单位
 *
 * @author Mr.Lai
 * @date 2023/3/22
 */
public enum TimeUnitEnum {

    /**
     * 小时
     */
    HOURS,

    /**
     * 天
     */
    DAYS,

    /**
     * 月
     */
    MONTH,

    /**
     * 年
     */
    YEARS,

    ;

    public static TimeUnitEnum getTimeUnit(String timeUnit) {
        return Stream.of(TimeUnitEnum.values()).filter(item -> item.name().equals(timeUnit)).findFirst().orElse(null);
    }

}
