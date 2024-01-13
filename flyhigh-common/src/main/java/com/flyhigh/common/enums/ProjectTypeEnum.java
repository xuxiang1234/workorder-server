package com.flyhigh.common.enums;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 项目分类
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter
public enum ProjectTypeEnum {

    /**
     * 资产类
     */
    PROJECT_TYPE_ASSET("1", "资产类"),

    /**
     * 建筑施工类
     */
    PROJECT_ARCHITECTURE_ASSET("2", "建筑施工类");


    private String type;

    private String desc;

    /**
     * 根据分类获取详细信息
     *
     * @param type
     * @return
     */
    public static String getDescByType(String type) {
        return Stream.of(ProjectTypeEnum.values())
                .filter(o -> o.getType().equals(type))
                .map(ProjectTypeEnum::getDesc).findFirst()
                .orElse(null);
    }

}