package com.weghst.pine.repository;

public class FindException extends RuntimeException {

    public FindException(String message) {
        super(message);
    }

    public FindException(String message, Throwable cause) {
        super(message, cause);
    }

}
