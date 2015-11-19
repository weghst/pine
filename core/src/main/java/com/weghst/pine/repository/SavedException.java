package com.weghst.pine.repository;

public class SavedException extends RuntimeException {

    public SavedException(String message) {
        super(message);
    }

    public SavedException(String message, Throwable cause) {
        super(message, cause);
    }

}
