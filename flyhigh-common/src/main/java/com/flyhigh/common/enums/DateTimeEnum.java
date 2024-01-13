package com.flyhigh.common.enums;

import com.flyhigh.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * 月、季度、年时间枚举类
 */
@AllArgsConstructor
@Getter
public enum DateTimeEnum {

    /**
     * 本月
     */
    MONTH(1, "本月"),

    /**
     * 本季
     */
    QUARTER(2, "本季"),

    /**
     * 本年
     */
    YEAR(3, "本年");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述信息
     */
    private final String desc;

    /**
     * 获取开始时间
     *
     * @param timeType
     * @return
     */
    public static Date getStartTime(Integer timeType) {
        if (MONTH.getType().equals(timeType)) {
            return DateUtils.getMonthFirstDayDateTime();
        }
        if (QUARTER.getType().equals(timeType)) {
            return DateUtils.getStartDayOfQuarter();
        }
        if (YEAR.getType().equals(timeType)) {
            return DateUtils.getYearFirstDayTime();
        }
        return null;
    }

    /**
     * 获取结束时间
     *
     * @param timeType
     * @return
     */
    public static Date getEndTime(Integer timeType) {
        if (MONTH.getType().equals(timeType)) {
            return DateUtils.getMonthLastDayDateTime();
        }
        if (QUARTER.getType().equals(timeType)) {
            return DateUtils.getEndDayOfQuarter();
        }
        if (YEAR.getType().equals(timeType)) {
            return DateUtils.getYearLastDayTime();
        }
        return null;
    }


}
