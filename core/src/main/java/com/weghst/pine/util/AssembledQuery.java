package com.weghst.pine.util;

import com.weghst.pine.Pines;

import java.util.HashMap;
import java.util.Map;

/**
 * 多条件复合搜索/查询类定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class AssembledQuery extends AbstractQuery<Map<String, String>> {

    private Map<String, String> q;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getQ() {
        return q;
    }

    /**
     * 设置搜索/查询条件.
     *
     * @param q 搜索/查询条件
     */
    public void setQ(Map<String, String> q) {
        this.q = new HashMap<>(q);
    }

    /**
     * 判断是否包涵指定的名称的搜索/查询参数.
     *
     * @param name 属性名称
     * @return true/false
     */
    public boolean contains(String name) {
        return (q != null && q.containsKey(name));
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return 字符串
     */
    public String getString(String name) {
        if (q == null) {
            return null;
        }
        return q.get(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param name 属性名称
     * @return boolean
     */
    public boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     * <p>
     * 属性值为下列表中值时都将返回{@code true}且不区分大小写.
     * <ul>
     * <li>y</li>
     * <li>yes</li>
     * <li>true</li>
     * <li>1</li>
     * </ul>
     * </p>
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return boolean
     * @see Pines#isTrue(String)
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Pines.isTrue(v);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return int
     */
    public int getInt(String name) {
        return getInt(name, 0);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return int
     */
    public int getInt(String name, int defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }
        return Integer.parseInt(v);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return long
     */
    public long getLong(String name) {
        return getLong(name, 0L);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return long
     */
    public long getLong(String name, long defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Long.parseLong(v);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return float
     */
    public float getFloat(String name) {
        return getFloat(name, 0F);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return float
     */
    public float getFloat(String name, float defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Float.parseFloat(v);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return double
     */
    public double getDouble(String name) {
        return getDouble(name, 0D);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return double
     */
    public double getDouble(String name, double defaultValue) {
        String v = getString(name);
        if (v == null) {
            return defaultValue;
        }

        return Double.parseDouble(v);
    }

    @Override
    public String toString() {
        return "AssembledQuery{" +
                "q=" + q +
                "} " + super.toString();
    }
}
