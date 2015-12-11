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

/**
 * 系统参数/业务参数提供者接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigurationProvider {

    /**
     * 设置参数.
     *
     * @param name  属性名称
     * @param value 参数值
     */
    void setProperty(String name, Object value);

    /**
     * 移除参数.
     *
     * @param name 属性名称
     */
    boolean removeProperty(String name);

    /**
     * 判断是否有配置指定参数.
     *
     * @param name 属性名称
     * @return boolean
     */
    boolean containsKey(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param name 属性名称
     * @return boolean
     */
    boolean getBoolean(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return boolean
     */
    boolean getBoolean(String name, boolean defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return int
     */
    int getInt(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return int
     */
    int getInt(String name, int defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return long
     */
    long getLong(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return long
     */
    long getLong(String name, long defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return float
     */
    float getFloat(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return float
     */
    float getFloat(String name, float defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return double
     */
    double getDouble(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return double
     */
    double getDouble(String name, double defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return String
     */
    String getString(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return String
     */
    String getString(String name, String defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code new String[0]}.
     *
     * @param name 属性名称
     * @return String[]
     */
    String[] getStringArray(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigDecimal
     */
    BigDecimal getBigDecimal(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    BigDecimal getBigDecimal(String name, String defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigInteger
     */
    BigInteger getBigInteger(String name);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigInteger
     */
    BigInteger getBigInteger(String name, String defaultValue);
}
