package com.weghst.pine.web.resource;

public class RestfulException extends RuntimeException {

    private int errorCode;

    public RestfulException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RestfulException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public RestfulException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
