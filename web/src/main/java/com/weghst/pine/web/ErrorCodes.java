package com.weghst.pine.web;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public enum ErrorCodes {

    /**
     * 服务内部错误.
     */
    E10000(10000, "服务内部错误"),
    /**
     * 参数错误.
     */
    E10100(10100, "参数错误"),
    /**
     * 用户未找到.
     */
    E12000(12000, "用户未找到");


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
