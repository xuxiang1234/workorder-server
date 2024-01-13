package com.flyhigh.common.permission;

import java.lang.annotation.*;

/**
 * @author huangjinhui
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

    DataColumn[] value();

    /**
     * 数据权限类型
     * 为NONE时，表示使用用户角色中的权限类型
     * 若不为NONE，则表示需要强制修改数据权限范围，此时会把角色的权限覆盖，仅仅使用指定的数据权限规则
     *
     * @return
     */
    DataScopeType[] scoptTypes() default DataScopeType.NONE;

}
