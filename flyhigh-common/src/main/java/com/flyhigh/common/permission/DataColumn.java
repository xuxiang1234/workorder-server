package com.flyhigh.common.permission;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataColumn {

    /**
     * 占位符关键字
     */
    String key() default "deptName";

    /**
     * 占位符替换值
     */
    String value() default "dept_id";


    /**
     * sql类型
     *
     * @return
     */
    DataSqlType sqlType() default DataSqlType.ORIGINAL;

}
