package com.weghst.pine.util;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class RedisUtils {

    /**
     * @param args
     * @return
     */
    public static String getCacheName(String... args) {
        StringBuilder sb = new StringBuilder(args[0]);
        for (int i = 1; i < args.length; i++) {
            sb.append(":").append(args[i]);
        }
        return sb.toString();
    }
}
