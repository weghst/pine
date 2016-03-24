package com.weghst.pine.util;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public class JsonException extends RuntimeException {

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
