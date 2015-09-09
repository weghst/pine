package com.weghst.pine.web;

/**
 * @author zouyong
 * @date 2015-09-05
 */
public class Test {
    public static void main(String[] args) {
        int x = 10000, y = 0, z = 0;
        for (int i = 0; ; i++) {
            y = x / 100 * 10;

            int m = x % 100;

            x = (x - y + m);

            z += y;

            System.out.println(m / 100.0 + ": " + x / 100.0 + ": " + y / 100.0 + ": " + z / 100.0);
            if (z / 100 >= 100) {
                System.out.println(i);
                break;
            }
        }
    }
}
