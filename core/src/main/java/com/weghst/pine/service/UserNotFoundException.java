package com.weghst.pine.service;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public class UserNotFoundException extends RuntimeException {

    /**
     *
     * @param message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
