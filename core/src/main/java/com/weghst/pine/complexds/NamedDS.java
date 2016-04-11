package com.weghst.pine.complexds;

import java.lang.annotation.*;


/**
 * 声明式数据源。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NamedDS {

    /**
     * 默认 Slave 数据源名称。
     */
    String DEFAULT_SLAVE_NAME = "default.slave";

    /**
     * Slave 数据源名称。
     *
     * @return Slave 数据源名称
     */
    String value() default DEFAULT_SLAVE_NAME;
}
