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

public interface ConfigurationProvider {

    boolean containsKey(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

    int getInt(String key);

    int getInt(String key, int defaultValue);

    long getLong(String key);

    long getLong(String key, long defaultValue);

    float getFloat(String key);

    float getFloat(String key, float defaultValue);

    double getDouble(String key);

    double getDouble(String key, double defaultValue);

    String getString(String key);

    String getString(String key, String defaultValue);

    String[] getStringArray(String key);

    String[] getStringArray(String key, String[] defaultValue);

    BigDecimal getBigDecimal(String key);

    BigDecimal getBigDecimal(String key, String defaultValue);

    BigInteger getBigInteger(String key);

    BigInteger getBigInteger(String key, String defaultValue);

}
