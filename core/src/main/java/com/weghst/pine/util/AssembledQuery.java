package com.weghst.pine.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zou Yong
 */
public class AssembledQuery extends AbstractQuery<Map<String, String>> {

    private Map<String, String> q;

    @Override
    public Map<String, String> getQ() {
        return q;
    }

    public void setQ(Map<String, String> q) {
        this.q = new HashMap<>(q);
    }

    public boolean contains(String name) {
        return (q != null && q.containsKey(name));
    }

    public String getString(String name) {
        if (q == null) {
            return null;
        }
        return q.get(name);
    }

    public int getInt(String name) {
        return getInt(name, 0);
    }

    public int getInt(String name, int defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }
        return Integer.parseInt(v);
    }

    public long getLong(String name) {
        return getLong(name, 0L);
    }

    public long getLong(String name, long defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Long.parseLong(v);
    }

    public float getFloat(String name) {
        return getFloat(name, 0F);
    }

    public float getFloat(String name, float defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Float.parseFloat(v);
    }

    public double getDouble(String name) {
        return getDouble(name, 0D);
    }

    public double getDouble(String name, double defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Double.parseDouble(v);
    }

}
