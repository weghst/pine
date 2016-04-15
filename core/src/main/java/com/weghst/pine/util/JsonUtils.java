package com.weghst.pine.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

import javax.json.JsonException;
import java.io.IOException;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.registerModule(new JSR353Module());
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
            throw new JsonException("读取JSON失败", e);
        }
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(byte[] src, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(src, valueType);
        } catch (IOException e) {
            throw new JsonException("读取JSON失败", e);
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
            throw new JsonException("输出JSON失败", e);
        }
    }

    /**
     * @param value
     * @return
     */
    public static byte[] writeValueAsBytes(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new JsonException("输出JSON失败", e);
        }
    }

    /**
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
