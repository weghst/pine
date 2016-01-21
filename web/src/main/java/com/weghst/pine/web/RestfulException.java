package com.weghst.pine.web;

import com.weghst.pine.web.ErrorCodes;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
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

    public RestfulException(ErrorCodes errorCodes) {
        this(errorCodes.getCode(), errorCodes.getMessage());
    }

    public RestfulException(ErrorCodes errorCodes, String message) {
        this(errorCodes.getCode(), message);
    }

    public RestfulException(ErrorCodes errorCodes, String message, Throwable cause) {
        this(errorCodes.getCode(), message, cause);
    }

    public RestfulException(ErrorCodes errorCodes, Throwable cause) {
        this(errorCodes, errorCodes.getMessage(), cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
