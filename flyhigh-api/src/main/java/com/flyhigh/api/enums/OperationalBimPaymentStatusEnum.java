package com.flyhigh.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author Mr.Lai
 * @date 2023/5/22
 */
@AllArgsConstructor
@Getter
public enum OperationalBimPaymentStatusEnum {

    /**
     * 已缴费
     */
    PAYMENT(1, "已缴费"),

    /**
     * 未缴费
     */
    OVERDUE(0, "未缴费"),

    ;

    private Integer type;

    private String desc;


    /**
     * 根据分类获取详细信息
     *
     * @param type
     * @return
     */
    public static String getDescByType(Integer type) {
        return Stream.of(OperationalBimPaymentStatusEnum.values())
                .filter(o -> o.getType().equals(type))
                .map(OperationalBimPaymentStatusEnum::getDesc).findFirst()
                .orElse(null);
    }

}
