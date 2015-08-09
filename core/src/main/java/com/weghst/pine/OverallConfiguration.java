package com.weghst.pine;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OverallConfiguration implements ConfigurationProvider {

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public boolean getBoolean(String key) {
        return false;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public int getInt(String key) {
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override
    public float getFloat(String key) {
        return 0;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override
    public double getDouble(String key) {
        return 0;
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return null;
    }

    @Override
    public String[] getStringArray(String key) {
        return new String[0];
    }

    @Override
    public String[] getStringArray(String key, String[] defaultValue) {
        return new String[0];
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String key, String defaultValue) {
        return null;
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return null;
    }

    @Override
    public BigInteger getBigInteger(String key, String defaultValue) {
        return null;
    }
}
