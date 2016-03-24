package com.weghst.pine.complexds;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ComplexDataSourceInterceptor {

    private ConcurrentMap<Object, Object> methodSignatures = new ConcurrentHashMap<>();

    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object obj = methodSignatures.get(methodSignature.getMethod());
        if (obj == null) {
            Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
            Method method = ReflectionUtils.findMethod(clazz, methodSignature.getName(),
                    methodSignature.getParameterTypes());

            // 查询目标实现是否声明
            DataSourceType dataSourceType = method.getAnnotation(DataSourceType.class);
            if (dataSourceType == null) {
                // 查询接口定义是否声明
                dataSourceType = methodSignature.getMethod().getAnnotation(DataSourceType.class);
                if (dataSourceType == null) {
                    methodSignatures.put(methodSignature.getMethod(), Boolean.FALSE);
                    obj = Boolean.FALSE;
                }
            }

            if (dataSourceType != null) {
                methodSignatures.put(methodSignature.getMethod(), dataSourceType);
                obj = dataSourceType;
            }
        }

        if (obj == Boolean.FALSE) {
            return proceedingJoinPoint.proceed();
        }

        try {
            DataSourceType dataSourceType = (DataSourceType) obj;
            DataSourceTypeValue.set(dataSourceType.value());
            return proceedingJoinPoint.proceed();
        } finally {
            DataSourceTypeValue.remove();
        }
    }

}
