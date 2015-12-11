package com.weghst.pine;

/**
 * PineException.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PineException extends RuntimeException {

    /**
     * @see RuntimeException(String)
     */
    public PineException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException(String, Throwable)
     */
    public PineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException(Throwable)
     */
    public PineException(Throwable cause) {
        super(cause);
    }
}
