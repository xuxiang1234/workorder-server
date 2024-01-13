package com.flyhigh.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 平台类型
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter

public enum PlatformEnum {

    /**
     * 系统管理
     */
    SYSTEM("system", "系统管理"),

    /**
     * 工单系统
     */
    WORK_ORDER("work_order", "工单系统"),

    /**
     * 运维管理系统
     */
    OPERATIONAL("operational", "运维管理系统"),

    ;

    /**
     * 站点编码
     */
    private String siteCode;

    /**
     * 站点描述
     */
    private String description;

    /**
     * 根据分类获取详细信息
     *
     * @param siteCode
     * @return
     */
    public static String getDescByType(String siteCode) {
        return Stream.of(PlatformEnum.values())
                .filter(o -> o.getSiteCode().equals(siteCode))
                .map(PlatformEnum::getDescription).findFirst()
                .orElse(null);
    }


}
