package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author zouyong
 * @date 2015-08-29
 */
public class PineModule extends SimpleModule {

    public PineModule() {
        addSerializer(boolean.class, new MyBooleanSerializer(true));
        addSerializer(Boolean.class, new MyBooleanSerializer(false));
    }
}
