package com.weghst.pine.web;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public enum ErrorCodes {

    UNKNOW(-1, "未知错误"),
    E10000(10000, "服务内部错误"),
    E10100(10100, "参数错误"),
    E10101(10101, "键值冲突"),
    E12000(12000, "用户未找到"),
    E12100(12100, "邮箱或者密码错误")
    // ------------
    ;

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
