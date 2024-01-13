package com.flyhigh.common.annotation;

import java.lang.annotation.*;

/**
 * 标注只能自己删除或者修改
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfUpdateOrDelete {

    /**
     * id
     *
     * @return
     */
    String value() default "#id";

    /**
     * 带校验的实体
     */
    Class[] entities();

}
