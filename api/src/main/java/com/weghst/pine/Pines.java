package com.weghst.pine;

import java.util.Calendar;
import java.util.TimeZone;

public final class Pines {

    private static final String TIME_ZONE_ID = ConfigUtils.getString("pine.timeZone", "GMT+00:00");
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone(TIME_ZONE_ID);

    /**
     *
     * @return
     */
    public static TimeZone timeZone() {
        return TIME_ZONE;
    }

    /**
     *
     * @return
     */
    public static Calendar calendar() {
        return Calendar.getInstance(timeZone());
    }

    /**
     *
     * @return
     */
    public static long currentTimeMillis() {
        return calendar().getTimeInMillis();
    }

    /**
     *
     * @return
     */
    public static long unixTimestamp() {
        return calendar().getTimeInMillis() / 1000;
    }
}
