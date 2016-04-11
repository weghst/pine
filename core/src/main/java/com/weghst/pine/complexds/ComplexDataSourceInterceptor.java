package com.weghst.pine.complexds;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 复合数据源拦截器。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ComplexDataSourceInterceptor {

    private final Map<Object, Object> namedMap = new HashMap<>();

    /**
     * @param proceedingJoinPoint 被拦截的方法
     * @return 方法执行结果
     * @throws Throwable
     */
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object obj = namedMap.get(methodSignature.getMethod());
        if (obj == null) {
            Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
            Method method = ReflectionUtils.findMethod(clazz, methodSignature.getName(),
                    methodSignature.getParameterTypes());

            NamedDS named = AnnotationUtils.findAnnotation(method, NamedDS.class);
            if (named != null) {
                obj = named;
            } else {
                obj = Boolean.FALSE;
            }
            namedMap.put(methodSignature.getMethod(), obj);
        }

        if (obj == Boolean.FALSE) {
            return proceedingJoinPoint.proceed();
        }

        try {
            NamedDS named = (NamedDS) obj;
            NamedDSUtils.set(named.value());
            return proceedingJoinPoint.proceed();
        } finally {
            NamedDSUtils.remove();
        }
    }

}
