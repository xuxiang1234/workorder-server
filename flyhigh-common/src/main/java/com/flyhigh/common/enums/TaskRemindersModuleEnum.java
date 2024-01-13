package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 任务提醒模块分类
 */
@AllArgsConstructor
@Getter
public enum TaskRemindersModuleEnum {

    /**
     * 待缴费提醒
     */
    PAYMENT_NOTICE(1, "待缴费"),

    /**
     * 工单提醒
     */
    WORK_ORDER_NOTICE(2, "工单提醒"),

    /**
     * 合同到期提醒
     */
    CONTRACT_EXPIRATION_NOTICE(3, "合同到期提醒"),

    /**
     * 开票提醒
     */
    INVOICING_NOTICE(4, "待开票"),

    ;

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述信息
     */
    private final String desc;

    /**
     * 根据分类获取描述信息
     *
     * @param type
     * @return
     */
    public static String getDescByType(Integer type) {
        return Stream.of(TaskRemindersModuleEnum.values())
                .filter(Objects::nonNull)
                .filter(o -> o.getType().equals(type))
                .map(TaskRemindersModuleEnum::getDesc).findFirst()
                .orElse(null);
    }

}
