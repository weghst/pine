package com.weghst.pine.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@RequestMapping(method = RequestMethod.DELETE)
@ResponseBody
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Delete {

    @AliasFor(annotation = RequestMapping.class, value = "value") String[] value() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "params") String[] params() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "headers") String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "consumes") String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "produces") String[] produces() default {};
}
