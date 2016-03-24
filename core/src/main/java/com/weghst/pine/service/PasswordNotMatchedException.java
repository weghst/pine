package com.weghst.pine.service;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public class PasswordNotMatchedException extends RuntimeException {

    /**
     *
     * @param message
     */
    public PasswordNotMatchedException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }
}
