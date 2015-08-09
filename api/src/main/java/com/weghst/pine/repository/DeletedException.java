package com.weghst.pine.repository;

public class DeletedException extends RuntimeException {

    public DeletedException(String message) {
        super(message);
    }

    public DeletedException(String message, Throwable cause) {
        super(message, cause);
    }

}
