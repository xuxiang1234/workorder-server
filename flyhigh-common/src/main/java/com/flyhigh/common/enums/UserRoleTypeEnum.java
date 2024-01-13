package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 用户角色
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter
public enum UserRoleTypeEnum {

    ADMIN(0, "超级管理员"),

    GROUP_COMPANY(1, "集团公司"),

    SUBSIDIARY(2, "子公司角色"),

    THIRD_MANAGER(3, "三级管理员");

    private final Integer roleType;

    private final String desc;


    /**
     * 根据roleType获取角色枚举
     *
     * @param roleType
     * @return
     */
    public static UserRoleTypeEnum getByRoleType(Long roleType) {
        if (Objects.isNull(roleType)) {
            return null;
        }
        return Stream.of(UserRoleTypeEnum.values()).filter(role -> role.roleType.equals(roleType.intValue())).findFirst().orElse(null);
    }
}
