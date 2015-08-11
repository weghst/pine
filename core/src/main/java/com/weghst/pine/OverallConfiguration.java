package com.weghst.pine;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Properties;

public class OverallConfiguration implements ConfigurationProvider {

    private static final PropertyPlaceholderHelper nonStrictHelper =
            new PropertyPlaceholderHelper("${", "}", ":", true);

    private Configuration configuration;

    public OverallConfiguration() {
        configuration = new MapConfiguration(System.getProperties());
    }

    @Override
    public void setProperty(String key, Object value) {
        configuration.setProperty(key, value);
    }

    @Override
    public boolean removeProperty(String key) {
        configuration.clearProperty(key);
        return true;
    }

    @Override
    public boolean containsKey(String key) {
        return configuration.containsKey(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return configuration.getBoolean(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return configuration.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return configuration.getLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key) {
        return configuration.getFloat(key, 0F);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return configuration.getFloat(key, defaultValue);
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return configuration.getDouble(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return configuration.getString(key, defaultValue);
    }

    @Override
    public String[] getStringArray(String key) {
        return configuration.getStringArray(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return configuration.getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key, String defaultValue) {
        try {
            BigDecimal value = configuration.getBigDecimal(key);
            if (value == null) {
                return (new BigDecimal(defaultValue));
            }
            return value;
        } catch (Exception e) {
            return (new BigDecimal(defaultValue));
        }
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return configuration.getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(String key, String defaultValue) {
        try {
            BigInteger value = configuration.getBigInteger(key);
            if (value == null) {
                return (new BigInteger(defaultValue));
            }
            return value;
        } catch (Exception e) {
            return (new BigInteger(defaultValue));
        }
    }

    private class CompositeProperties extends Properties {

        private Map<String, String> properties;

        @Override
        public String getProperty(String key) {
            String value = properties.get(key);
            if (value == null) {
                value = System.getProperty(key);
            }
            return value;
        }

        @Override
        public synchronized Object setProperty(String key, String value) {
            properties.put(key, value);
            return value;
        }
    }
}
