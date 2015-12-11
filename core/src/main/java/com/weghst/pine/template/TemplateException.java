package com.weghst.pine.template;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class TemplateException extends Exception {

    public TemplateException(String message) {
        super(message);
    }

    public TemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}
