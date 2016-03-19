/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.pine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 系统参数及业务参数工具类.
 * <p>
 * 通过{@code META-INF/services/com.weghst.pine.ConfigurationProvider}配置参数提供实现,
 * 如果未设置则默认使用{@link DefaultConfiguration}
 * </p>
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ConfigUtils {

    private final static String DEFAULT_CONFIGURATION_CLASS = "com.weghst.pine.DefaultConfiguration";
    private final static ConfigurationProvider configurationProvider;

    static {
        ServiceLoader<ConfigurationProvider> serviceLoader = ServiceLoader.load(ConfigurationProvider.class);
        Iterator<ConfigurationProvider> it = serviceLoader.iterator();
        if (it.hasNext()) {
            configurationProvider = it.next();
        } else {
            try {
                Class<?> clazz = Class.forName(DEFAULT_CONFIGURATION_CLASS);
                configurationProvider = (ConfigurationProvider) clazz.newInstance();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * 设置参数.
     *
     * @param name  属性名称
     * @param value 参数值
     */
    public static void setProperty(String name, Object value) {
        configurationProvider.setProperty(name, value);
    }

    /**
     * 移除参数.
     *
     * @param name 属性名称
     */
    public static void removeProperty(String name) {
        configurationProvider.removeProperty(name);
    }

    /**
     * 判断是否有配置指定参数.
     *
     * @param name 属性名称
     * @return boolean
     */
    public static boolean containsKey(String name) {
        return configurationProvider.containsKey(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param name 属性名称
     * @return boolean
     */
    public static boolean getBoolean(String name) {
        return configurationProvider.getBoolean(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return boolean
     */
    public static boolean getBoolean(String name, boolean defaultValue) {
        return configurationProvider.getBoolean(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return int
     */
    public static int getInt(String name) {
        return configurationProvider.getInt(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(String name, int defaultValue) {
        return configurationProvider.getInt(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return long
     */
    public static long getLong(String name) {
        return configurationProvider.getLong(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return long
     */
    public static long getLong(String name, long defaultValue) {
        return configurationProvider.getLong(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return float
     */
    public static float getFloat(String name) {
        return configurationProvider.getFloat(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return float
     */
    public static float getFloat(String name, float defaultValue) {
        return configurationProvider.getFloat(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return double
     */
    public static double getDouble(String name) {
        return configurationProvider.getDouble(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return double
     */
    public static double getDouble(String name, double defaultValue) {
        return configurationProvider.getDouble(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return String
     */
    public static String getString(String name) {
        return configurationProvider.getString(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(String name, String defaultValue) {
        return configurationProvider.getString(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code new String[0]}.
     *
     * @param name 属性名称
     * @return String[]
     */
    public static String[] getStringArray(String name) {
        return configurationProvider.getStringArray(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name) {
        return configurationProvider.getBigDecimal(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name, String defaultValue) {
        return configurationProvider.getBigDecimal(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String name) {
        return configurationProvider.getBigInteger(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String name, String defaultValue) {
        return configurationProvider.getBigInteger(name, defaultValue);
    }
}
