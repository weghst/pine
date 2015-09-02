package com.weghst.pine.web.util;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {

    public static String getContextPath(HttpServletRequest request) {
        String cp = request.getContextPath();
        if ("/".equals(cp)) {
            return "";
        }
        return cp;
    }
}
