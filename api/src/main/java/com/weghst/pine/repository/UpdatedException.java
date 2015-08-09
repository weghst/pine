package com.weghst.pine.repository;

public class UpdatedException extends RuntimeException {

    public UpdatedException(String message) {
        super(message);
    }

    public UpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

}
