package com.weghst.pine.util.lock;

/**
 * 同步锁异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LockException extends RuntimeException {

    /**
     * 通过详细信息构造同步锁异常.
     *
     * @param message 详细信息
     */
    public LockException(String message) {
        super(message);
    }

    /**
     * 通过详细信息与目标异常构建同步锁异常.
     *
     * @param message 详细信息
     * @param cause   目标异常
     */
    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
