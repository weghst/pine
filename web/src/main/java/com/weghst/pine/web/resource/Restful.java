package com.weghst.pine.web.resource;

import com.weghst.pine.web.ErrorCodes;

import com.weghst.pine.web.vo.PrimitiveVo;
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

    /**
     * @param code
     * @param message
     * @return
     */
    public static Restful with(int code, String message) {
        Restful restful = new Restful();
        restful.errorCode = code;
        restful.errorMessage = message;

        return restful;
    }

    /**
     * @param result
     * @return
     */
    public static Restful with(Object result) {
        Assert.notNull(result);

        Restful restful = new Restful();
        restful.result = result;
        return restful;
    }

    /**
     * @param value
     * @return
     */
    public static Restful withPrimitive(Object value) {
        return with(new PrimitiveVo(value));
    }

    /**
     * @param errorCode
     * @return
     */
    public static Restful withErrorCode(ErrorCodes errorCode) {
        Assert.notNull(errorCode);
        return with(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * @param e
     * @return
     */
    public static Restful withException(RestfulException e) {
        return with(e.getErrorCode(), e.getMessage());
    }
}
