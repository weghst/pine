package com.weghst.pine.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class BeanUtils {

    private static boolean ENABLED_CGLIB = Boolean.getBoolean("pine.bean.utils.enabledCglib");
    private static ConcurrentMap<String, BeanCopier> beanCopiers;

    static {
        if (ENABLED_CGLIB) {
            beanCopiers = new ConcurrentHashMap<>();
        }
    }

    /**
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        Assert.notNull(source);
        Assert.notNull(target);

        if (ENABLED_CGLIB) {
            String key = source.getClass().getName() + target.getClass().getName();
            BeanCopier beanCopier = beanCopiers.get(key);
            if (beanCopier == null) {
                beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            }
            beanCopier.copy(source, target, null);
        } else {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        }
    }
}
