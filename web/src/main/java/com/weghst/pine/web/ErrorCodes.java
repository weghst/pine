package com.weghst.pine.web;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-09-30 14:47
 */
public enum ErrorCodes {

    E10000(10000, "服务内部错误");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
