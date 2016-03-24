package com.weghst.pine.complexds;

import java.lang.annotation.*;

/**
 * 声明数据源类型。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceType {

    /**
     * Slave 数据源名称。
     *
     * @return Slave 数据源名称
     */
    String value();
}
