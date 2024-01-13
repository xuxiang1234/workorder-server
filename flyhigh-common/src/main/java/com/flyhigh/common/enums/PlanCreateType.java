package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @ClassName PlanCreateType
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/10 16:08
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum PlanCreateType {

    MONTH(1, "月"),
    QUARTER(2, "季度"),
    HALF_YEAR(3, "半年"),
    YEAR(4, "全年");

    private Integer type;

    private String desc;

    public static PlanCreateType getByType(Integer type) {
        return Stream.of(PlanCreateType.values()).filter(data -> data.getType().equals(type)).findFirst().orElse(null);
    }
}
