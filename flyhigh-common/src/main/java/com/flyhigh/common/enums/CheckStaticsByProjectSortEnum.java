package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 检查统计排序方式枚举
 */
@Getter
@AllArgsConstructor
public enum CheckStaticsByProjectSortEnum {

    /**
     * 检查总数倒序
     */
    CHECK_COUNT_DESC("checkCount desc"),
    /**
     * 检查总数正序
     */
    CHECK_COUNT_ASC("checkCount asc"),

    /**
     * 隐患总数倒序
     */
    ALL_TROUBLE_COUNT_DESC("allTroubleCount desc"),
    /**
     * 隐患总数正序
     */
    ALL_TROUBLE_COUNT_ASC("allTroubleCount asc"),
    ;

    private String sortSql;

}
