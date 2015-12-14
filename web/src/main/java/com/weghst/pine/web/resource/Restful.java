package com.weghst.pine.web.resource;

import com.weghst.pine.web.ErrorCodes;

import org.springframework.util.Assert;

/**
 * Restful 返回数据格式定义.
 * <p>
 * {@code errorCode} 如果不等于0则返回错误信息: {@code {"errorCode":10000, "errorMessage": "错误描述"}}</b>.
 * 反之处理成功则直接将 {@code result} 序列化为 {@code JSON} 数据格式返回.
 * </p>
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Restful {

    private int errorCode;
    private String errorMessage;

    private Object result;

    /**
     * 构建一个返回成功数据的 {@code Restful} 对象.
     *
     * @param result 返回数据对象
     */
    public Restful(Object result) {
        this.result = result;
    }

    /**
     * 构建一个返回错误信息的 {@code Restful} 对象.
     *
     * @param errorCodes 错误码
     */
    public Restful(ErrorCodes errorCodes) {
        Assert.notNull(errorCodes);

        this.errorCode = errorCodes.getCode();
        this.errorMessage = errorCodes.getMessage();
    }

    /**
     * 构建一个返回错误信息的 {@code Restful} 对象.
     *
     * @param e 错误异常对象
     */
    public Restful(RestfulException e) {
        this(e.getErrorCode(), e.getMessage());
    }

    /**
     * 构建一个返回错误信息的 {@code Restful} 对象.
     *
     * @param errorCode    错误码
     * @param errorMessage 错误描述
     */
    public Restful(int errorCode, String errorMessage) {
        Assert.isTrue(errorCode > 0);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 返回错误码.
     *
     * @return 错误码
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 返回错误描述.
     *
     * @return 错误描述
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 返回成功的数据对象.
     *
     * @return 数据对象
     */
    public Object getResult() {
        return result;
    }
}
