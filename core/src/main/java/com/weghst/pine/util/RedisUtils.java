package com.weghst.pine.util;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class RedisUtils {

    public static String getCacheName(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(":").append(s);
        }
        return sb.toString();
    }
}
