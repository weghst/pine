package com.weghst.pine.complexds;

/**
 * 数据源类型上下文属性值。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class DataSourceTypeValue {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 获取数据源类型。
     *
     * @return String
     */
    public static String get() {
        return threadLocal.get();
    }

    /**
     * 设置数据源类型。
     *
     * @param type 数据源类型
     */
    public static void set(String type) {
        threadLocal.set(type);
    }

    /**
     * 移除数据源类型。
     */
    public static void remove() {
        threadLocal.remove();
    }
}
