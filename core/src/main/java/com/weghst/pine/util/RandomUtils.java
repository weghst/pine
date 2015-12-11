package com.weghst.pine.util;

import java.util.Random;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class RandomUtils {

    public static final int N100000 = 100000;
    public static final int N999999 = 999999;

    private static final Random RANDOM = new Random();

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static int nextInt(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    /**
     *
     * @return
     */
    public static int nextCode6() {
        return nextInt(N100000, N999999);
    }
}
