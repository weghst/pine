package com.weghst.pine.web.controller;

import com.weghst.pine.Constants;
import com.weghst.pine.web.ErrorCodes;
import com.weghst.pine.web.RestfulException;
import com.weghst.pine.web.vo.ErrorResult;
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
        HttpServletRequest req = webRequest.getNativeRequest(HttpServletRequest.class);
        String requestURI = req.getRequestURI();

        if (isRest(requestURI)) {
            HttpHeaders headers = newJsonHttpHeaders();

            ErrorResult result = new ErrorResult();
            result.setErrorCode(ErrorCodes.E10100.getCode());
            result.setErrorMessage("参数类型错误[" + e.getName() + "] >>> " + e.getMessage());
            return handleExceptionInternal(e, result, headers, HttpStatus.BAD_REQUEST, webRequest);
        }

        return null;
    }

    @ExceptionHandler(RestfulException.class)
    public ResponseEntity<Object> handleRestfulException(RestfulException e, NativeWebRequest webRequest) {
        HttpHeaders headers = newJsonHttpHeaders();

        ErrorResult result = new ErrorResult();
        result.setErrorCode(e.getErrorCode());
        result.setErrorMessage(e.getMessage());
        return handleExceptionInternal(e, result, headers, HttpStatus.BAD_REQUEST, webRequest);
    }

    private HttpHeaders newJsonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private boolean isRest(String uri) {
        return uri.startsWith(restfulPrefix);
    }
}
