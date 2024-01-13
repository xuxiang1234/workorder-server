package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 审核状态枚举
 *
 * @author Mr.Lai
 * @date 2023/1/31
 */
@AllArgsConstructor
@Getter
public enum CommApproveStatusEnum {

    /**
     * 待处理
     */
    PENDING(0, "待处理"),

    /**
     * 通过
     */
    SUCCESS(1, "通过"),

    /**
     * 不通过
     */
    FAILED(2, "不通过");

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
        return Stream.of(CommApproveStatusEnum.values())
                .filter(Objects::nonNull)
                .filter(o -> o.getType().equals(type))
                .map(CommApproveStatusEnum::getDesc).findFirst()
                .orElse(null);
    }

}
