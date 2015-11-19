package com.weghst.pine;

public class PineException extends RuntimeException {

    /**
     * @see #RuntimeException(String)
     */
    public PineException(String message) {
        super(message);
    }

    /**
     * @see #RuntimeException(String, Throwable)
     */
    public PineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see #RuntimeException(Throwable)
     */
    public PineException(Throwable cause) {
        super(cause);
    }

    /**
     * @see #RuntimeException(String, Throwable, boolean, boolean)
     */
    public PineException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
