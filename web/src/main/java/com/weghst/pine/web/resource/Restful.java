package com.weghst.pine.web.resource;

import com.weghst.pine.web.ErrorCodes;

import org.springframework.util.Assert;

public class Restful {

    private int errorCode;
    private String errorMessage;

    private Object result;

    public Restful(Object result) {
        Assert.notNull(result);

        this.result = result;
    }

    public Restful(ErrorCodes errorCodes) {
        Assert.notNull(errorCodes);

        this.errorCode = errorCodes.getCode();
        this.errorMessage = errorCodes.getMessage();
    }

    public Restful(RestfulException e) {
        this(e.getErrorCode(), e.getMessage());
    }

    public Restful(int errorCode, String errorMessage) {
        Assert.isTrue(errorCode > 0);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getResult() {
        return result;
    }

}
