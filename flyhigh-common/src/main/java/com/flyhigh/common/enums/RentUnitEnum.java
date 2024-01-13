package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;


/**
 * 租金单位枚举
 *
 * @author Mr.Lai
 * @date 2023/3/18
 */
@AllArgsConstructor
@Getter
public enum RentUnitEnum {

    /**
     * 月
     */
    MONTH(1, 1),

    /**
     * 年
     */
    YEAR(2, 12);

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 月份
     */
    private final Integer month;

    /**
     * 获取租金单位
     * 默认为月
     *
     * @param type
     * @return
     */
    public static RentUnitEnum getRentUnitByType(Integer type) {
        return Stream.of(RentUnitEnum.values())
                .filter(Objects::nonNull)
                .filter(o -> o.getType().equals(type)).findFirst()
                .orElse(RentUnitEnum.MONTH);
    }

}
