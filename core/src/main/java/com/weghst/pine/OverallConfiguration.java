package com.weghst.pine;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 较全面的属性配置实现.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class OverallConfiguration implements ConfigurationProvider {

    private static final PropertyPlaceholderHelper nonStrictHelper =
            new PropertyPlaceholderHelper("${", "}", ":", true);

    private Properties properties;

    public OverallConfiguration() {
        properties = new CompositeProperties();
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.setProperty(key, value.toString());
    }

    @Override
    public boolean removeProperty(String key) {
        properties.remove(key);
        return true;
    }

    @Override
    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return Boolean.parseBoolean(v);
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return Integer.parseInt(v);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return Long.parseLong(v);
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0F);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return Float.parseFloat(v);
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return Double.parseDouble(v);
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String defaultValue) {
        String v = properties.getProperty(key);
        if (v == null) {
            return defaultValue;
        }

        v = nonStrictHelper.replacePlaceholders(v, properties);
        return v;
    }

    @Override
    public String[] getStringArray(String key) {
        String v = getString(key);
        if (v == null) {
            return null;
        }

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        char pc = '\0';
        for (char c : v.toCharArray()) {
            if (c == ',') {
                if (pc == '\\') {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(c);
                    continue;
                }

                list.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }

            sb.append(c);
            pc = c;
        }
        list.add(sb.toString());

        return list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        String v = getString(key);
        if (v == null) {
            return null;
        }

        return new BigDecimal(v);
    }

    @Override
    public BigDecimal getBigDecimal(String key, String defaultValue) {
        String v = getString(key, defaultValue);
        if (v == null) {
            return new BigDecimal(defaultValue);
        }

        return new BigDecimal(v);
    }

    @Override
    public BigInteger getBigInteger(String key) {
        String v = getString(key);
        if (v == null) {
            return null;
        }

        return new BigInteger(v);
    }

    @Override
    public BigInteger getBigInteger(String key, String defaultValue) {
        String v = getString(key, defaultValue);
        if (v == null) {
            return new BigInteger(defaultValue);
        }

        return new BigInteger(v);
    }

    private class CompositeProperties extends Properties {

        private Map<String, String> map = new HashMap<>();

        @Override
        public boolean containsKey(Object key) {
            return map.containsKey(key);
        }

        @Override
        public String getProperty(String key) {
            String value = map.get(key);
            if (value == null) {
                value = System.getProperty(key);
            }
            return value;
        }

        @Override
        public synchronized Object setProperty(String key, String value) {
            map.put(key, value);
            return value;
        }

        @Override
        public synchronized Object remove(Object key) {
            return map.remove(key);
        }
    }
}
