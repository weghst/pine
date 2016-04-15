package com.weghst.pine.service;

import com.weghst.pine.PineException;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class SmsException extends PineException {

    public SmsException(String message) {
        super(message);
    }

    public SmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
