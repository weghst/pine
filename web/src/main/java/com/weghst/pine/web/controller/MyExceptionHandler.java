package com.weghst.pine.web.controller;

import com.weghst.pine.Constants;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.RestfulException;
import com.weghst.pine.web.vo.ErrorResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    private String restfulPrefix = System.getProperty(Constants.RESTFUL_PATH_PREFIX_PROP);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleGlobalException(MethodArgumentTypeMismatchException e,
                                                        NativeWebRequest webRequest) {
        if (isRestful(webRequest)) {
            return handleExceptionToJson(e, webRequest, HttpStatus.BAD_REQUEST, ErrorCodes.E10100.getCode(),
                    "参数类型错误[" + e.getName() + "] >>> " + e.getMostSpecificCause().getMessage());
        }
        return null;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e, NativeWebRequest webRequest) {
        if (isRestful(webRequest)) {
            return handleExceptionToJson(e, webRequest, HttpStatus.CONFLICT, ErrorCodes.E10101.getCode(),
                    "重复的键值 >>> " + e.getMostSpecificCause().getMessage());
        }
        return null;
    }

    @ExceptionHandler(RestfulException.class)
    public ResponseEntity<Object> handleRestfulException(RestfulException e, NativeWebRequest webRequest) {
        return handleExceptionToJson(e, webRequest, HttpStatus.BAD_REQUEST, e.getErrorCode(), e.getMessage());
    }

    private ResponseEntity<Object> handleExceptionToJson(Exception e, NativeWebRequest webRequest,
                                                         HttpStatus httpStatus, int errorCode, String errorMessage) {
        HttpHeaders headers = newJsonHttpHeaders();

        ErrorResult result = new ErrorResult();
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return handleExceptionInternal(e, result, headers, httpStatus, webRequest);
    }

    private HttpHeaders newJsonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private boolean isRestful(NativeWebRequest request) {
        HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);
        return req.getRequestURI().startsWith(restfulPrefix);
    }
}
