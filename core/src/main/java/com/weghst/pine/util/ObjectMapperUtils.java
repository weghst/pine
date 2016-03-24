package com.weghst.pine.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ObjectMapperUtils {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    private ObjectMapperUtils() {
        throw new AssertionError("No new instance");
    }

    /**
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            throw new JsonException("读取 JSON 错误", e);
        }
    }

    /**
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonException("转换 JSON 错误", e);
        }
    }

}
