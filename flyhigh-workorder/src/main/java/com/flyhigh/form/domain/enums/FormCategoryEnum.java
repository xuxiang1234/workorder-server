package com.flyhigh.form.domain.enums;

import com.flyhigh.api.enums.SystemIdentification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 表单类别枚举
 *
 * @author Mr.Lai
 * @date 2023/2/2
 */
@AllArgsConstructor
@Getter
public enum FormCategoryEnum {

    /**
     * 评分表单-租赁单元
     */
    LEASE_UNIT_SCORE_FORM(SystemIdentification.OPERATIONAL_SCORE, "LEASE_UNIT_SCORE_FORM", "评分表单-租赁单元"),

    /**
     * 评分表单-入驻商家
     */
    TENANT_SCORE_FORM(SystemIdentification.OPERATIONAL_SCORE, "TENANT_SCORE_FORM", "评分表单-入驻商家"),

    ;

    /**
     * 系统模块
     */
    private final SystemIdentification systemIdentification;

    /**
     * 类型
     */
    private final String type;

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
    public static String getDescByType(String type) {
        return Stream.of(FormCategoryEnum.values())
                .filter(Objects::nonNull)
                .filter(o -> o.getType().equals(type))
                .map(FormCategoryEnum::getDesc).findFirst()
                .orElse(null);
    }


}
