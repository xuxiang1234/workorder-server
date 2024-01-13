package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName FlowUserTypeEnum
 * @Description 审批用户类型 1 单人 2 多人
 * @Author huangjinhui
 * @Date 2022/3/21 17:16
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum FlowUserTypeEnum {

    /**
     * 单人
     */
    SINGLE(1, "单人"),

    /**
     * 多人
     */
    MANY_PEOPLE(2, "多人");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述信息
     */
    private final String desc;
}
