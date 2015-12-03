package com.weghst.pine.util.lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-12-02 14:05
 */
public class LockException extends RuntimeException {

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
