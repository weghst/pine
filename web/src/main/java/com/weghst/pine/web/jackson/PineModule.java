package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Pine Jackson 序列化配置.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PineModule extends SimpleModule {

    public PineModule() {
        addSerializer(boolean.class, new MyBooleanSerializer(true));
        addSerializer(Boolean.class, new MyBooleanSerializer(false));
    }

}
