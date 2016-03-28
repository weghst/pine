package com.weghst.pine.complexds;

import org.springframework.core.NamedThreadLocal;

/**
 * 数据源类型上下文属性值。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class NamedDSUtils {

    private static final ThreadLocal<String> contextName = new NamedThreadLocal<>("Named data source");

    /**
     * 获取数据源类型。
     *
     * @return String
     */
    public static String get() {
        return contextName.get();
    }

    /**
     * 设置数据源类型。
     *
     * @param name 数据源类型
     */
    public static void set(String name) {
        contextName.set(name);
    }

    /**
     * 移除数据源类型。
     */
    public static void remove() {
        contextName.remove();
    }
}
