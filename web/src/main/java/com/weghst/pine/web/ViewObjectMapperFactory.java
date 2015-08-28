package com.weghst.pine.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

/**
 * @author Kevin Zou
 */
public final class ViewObjectMapperFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JSR353Module());
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
