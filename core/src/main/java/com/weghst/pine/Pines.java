package com.weghst.pine;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Pine 系统工具类.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class Pines {

    private static final String TIME_ZONE_ID = System.getProperty("pine.timeZone", "GMT+00:00");
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone(TIME_ZONE_ID);

    /**
     * 返回 Pine 当前时区.
     * <pre>
     * 设置系统时区:
     * System.setProperty("pine.timeZone", "GMT+00:00")
     * </pre>
     *
     * @return 时区
     */
    public static TimeZone timeZone() {
        return TIME_ZONE;
    }

    /**
     * 返回 Pine 日历, 日历时区为{@link #timeZone()}.
     *
     * @return 日历
     */
    public static Calendar calendar() {
        return Calendar.getInstance(timeZone());
    }

    /**
     * 返回 Pine 当前时间戳, 时区为{@link #timeZone()}.
     *
     * @return 当前时间戳
     */
    public static long currentTimeMillis() {
        return calendar().getTimeInMillis();
    }

    /**
     * 返回 Pine Unix 时间戳, 时区为{@link #timeZone()}.
     *
     * @return Unix 时间戳
     */
    public static long unixTimestamp() {
        return calendar().getTimeInMillis() / 1000;
    }

    /**
     * 返回指定参数是否为{@code Boolean}类型.
     * <p>
     * 下列参数都将被视为一个{@code Boolean}值, 且不区分大小写.
     * <ul>
     * <li>y</li>
     * <li>yes</li>
     * <li>true</li>
     * <li>1</li>
     * </ul>
     * </p>
     *
     * @param v 属性值
     * @return boolean
     */
    public static boolean isTrue(String v) {
        return "y".equalsIgnoreCase(v)
                || "yes".equalsIgnoreCase(v)
                || "true".equalsIgnoreCase(v)
                || "1".equalsIgnoreCase(v);
    }
}
