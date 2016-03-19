package com.weghst.pine.complexds;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public final class DataSourceTypeValue {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String get() {
        return threadLocal.get();
    }

    /**
     * @param name
     */
    public static void set(String name) {
        threadLocal.set(name);
    }

    /**
     *
     */
    public static void remove() {
        threadLocal.remove();
    }
}
