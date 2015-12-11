package com.weghst.pine.service;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class EmailValidatedException extends Exception {

    public EmailValidatedException(String message) {
        super(message);
    }

    public EmailValidatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
