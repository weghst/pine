package com.weghst.pine;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ObjectMapperFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
//        OBJECT_MAPPER.enableDefaultTyping();
//        OBJECT_MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
