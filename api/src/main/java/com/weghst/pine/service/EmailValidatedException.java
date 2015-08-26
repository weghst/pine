package com.weghst.pine.service;

public class EmailValidatedException extends Exception {

    public EmailValidatedException(String message) {
        super(message);
    }

    public EmailValidatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
