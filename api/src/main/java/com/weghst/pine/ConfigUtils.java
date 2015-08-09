/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
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

public final class ConfigUtils {

    private final static ConfigurationProvider configurationProvider;

    static {
        ServiceLoader<ConfigurationProvider> serviceLoader = ServiceLoader.load(
                ConfigurationProvider.class);
        Iterator<ConfigurationProvider> it = serviceLoader.iterator();
        if (it.hasNext()) {
            configurationProvider = it.next();
        } else {
            configurationProvider = new DefaultConfiguration();
        }
    }

    public static boolean containsKey(String key) {
        return configurationProvider.containsKey(key);
    }

    public static boolean getBoolean(String key) {
        return configurationProvider.getBoolean(key);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return configurationProvider.getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return configurationProvider.getInt(key);
    }

    public static int getInt(String key, int defaultValue) {
        return configurationProvider.getInt(key, defaultValue);
    }

    public static long getLong(String key) {
        return configurationProvider.getLong(key);
    }

    public static long getLong(String key, long defaultValue) {
        return configurationProvider.getLong(key, defaultValue);
    }

    public static float getFloat(String key) {
        return configurationProvider.getFloat(key);
    }

    public static float getFloat(String key, float defaultValue) {
        return configurationProvider.getFloat(key, defaultValue);
    }

    public static double getDouble(String key) {
        return configurationProvider.getDouble(key);
    }

    public static double getDouble(String key, double defaultValue) {
        return configurationProvider.getDouble(key, defaultValue);
    }

    public static String getString(String key) {
        return configurationProvider.getString(key);
    }

    public static String getString(String key, String defaultValue) {
        return configurationProvider.getString(key, defaultValue);
    }

    public static String[] getStringArray(String key) {
        return configurationProvider.getStringArray(key);
    }

    public static String[] getStringArray(String key, String[] defaultValue) {
        return configurationProvider.getStringArray(key, defaultValue);
    }

    public static BigDecimal getBigDecimal(String key) {
        return configurationProvider.getBigDecimal(key);
    }

    public static BigDecimal getBigDecimal(String key, String defaultValue) {
        return configurationProvider.getBigDecimal(key, defaultValue);
    }

    public static BigInteger getBigInteger(String key) {
        return configurationProvider.getBigInteger(key);
    }

    public static BigInteger getBigInteger(String key, String defaultValue) {
        return configurationProvider.getBigInteger(key, defaultValue);
    }
}
