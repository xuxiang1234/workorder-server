package com.flyhigh.common.enums;

import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 月份枚举
 *
 * @ClassName MonthEnum
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/10 15:47
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MonthEnum {

    JANUARY("1", "1", "1"),
    FEBRUARY("2", "1", "1"),
    MARCH("3", "1", "1"),
    APRIL("4", "2", "1"),
    MAY("5", "2", "1"),
    JUNE("6", "2", "1"),
    JULY("7", "3", "2"),
    AUGUST("8", "3", "2"),
    SEPTEMBER("9", "3", "2"),
    OCTOBER("10", "4", "2"),
    NOVEMBER("11", "4", "2"),
    DECEMBER("12", "4", "2");

    /**
     * 月份
     */
    private String name;
    /**
     * 季度
     */
    private String quarter;

    /**
     * 年度区间 1 上半年 2 下半年
     */
    private String yearSection;


    public static List<String> getMonth(Integer createType, String createTypeValue, String year) {
        Predicate<MonthEnum> predicate = getPredicate(createType, createTypeValue);
        // 当前月份
        String currentDate = DateUtils.toDate();
        return Stream.of(MonthEnum.values())
                .filter(predicate).map(MonthEnum::getName)
                .filter(item -> currentDate.compareTo(year.concat(StringUtils.leftPad(item, 2, "0"))) < 0)
                .collect(Collectors.toList());
    }

    /**
     * 根据不同的类型，返回Predicate
     *
     * @param createTypeValue
     * @return
     */
    private static Predicate<MonthEnum> getPredicate(Integer createType, String createTypeValue) {
        // 如果是月度
        if (createType.equals(PlanCreateType.MONTH.getType())) {
            return monthEnum -> monthEnum.getName().equals(createTypeValue);
        }
        // 如果是季度
        if (createType.equals(PlanCreateType.QUARTER.getType())) {
            return monthEnum -> monthEnum.getQuarter().equals(createTypeValue);
        }
        // 如果是上下半年
        if (createType.equals(PlanCreateType.HALF_YEAR.getType())) {
            return monthEnum -> monthEnum.getYearSection().equals(createTypeValue);
        }
        // 兜底，返回全年
        return monthEnum -> true;
    }

}
