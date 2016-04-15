package com.weghst.pine.service;

import com.weghst.pine.PineException;

/**
 * 不正确的验证码异常。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class CodeIncorrectException extends PineException {

    public CodeIncorrectException(String message) {
        super(message);
    }

    public CodeIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
