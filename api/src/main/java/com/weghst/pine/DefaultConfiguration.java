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

class DefaultConfiguration implements ConfigurationProvider {

    static final String[] EMPTY_STRING_ARRAY = new String[0];

    @Override
    public boolean containsKey(String key) {
        return System.getProperty(key) != null;
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        try {
            return Boolean.parseBoolean(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0F);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        try {
            return Float.parseFloat(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String defaultValue) {
        String v = System.getProperty(key);
        return v == null ? defaultValue : v;
    }

    @Override
    public String[] getStringArray(String key) {
        return getStringArray(key, EMPTY_STRING_ARRAY);
    }

    @Override
    public String[] getStringArray(String key, String[] defaultValue) {
        return defaultValue;
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key, null);
    }

    @Override
    public BigDecimal getBigDecimal(String key, String defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            v = defaultValue;
        }

        return (new BigDecimal(v));
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return getBigInteger(key, null);
    }

    @Override
    public BigInteger getBigInteger(String key, String defaultValue) {
        String v = System.getProperty(key);
        if (v == null) {
            v = defaultValue;
        }

        return (new BigInteger(v));
    }

}
