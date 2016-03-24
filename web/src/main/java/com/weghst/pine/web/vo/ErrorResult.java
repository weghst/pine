package com.weghst.pine.web.vo;

import com.weghst.pine.web.ErrorCodes;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ErrorResult {

    private int errorCode;
    private String errorMessage;

    public ErrorResult() {
    }

    public ErrorResult(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResult(ErrorCodes errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
