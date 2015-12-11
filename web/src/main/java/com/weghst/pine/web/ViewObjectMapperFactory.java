package com.weghst.pine.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import com.weghst.pine.web.jackson.PineModule;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ViewObjectMapperFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JSR353Module());
        OBJECT_MAPPER.registerModule(new PineModule());

        // OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        // OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

}
