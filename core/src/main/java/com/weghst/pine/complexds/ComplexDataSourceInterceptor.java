package com.weghst.pine.complexds;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.util.ReflectionUtils;

/**
 * 复合数据源拦截器。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ComplexDataSourceInterceptor {

    private final ConcurrentMap<Object, Object> namedMap = new ConcurrentHashMap<>();

    /**
     * @param proceedingJoinPoint 被拦截的方法
     * @return 方法执行结果
     * @throws Throwable
     */
    public Object invoke(MethodInvocationProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object obj = namedMap.get(methodSignature.getMethod());
        if (obj == null) {
            Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
            Method method = ReflectionUtils.findMethod(clazz, methodSignature.getName(),
                    methodSignature.getParameterTypes());

            // 查询目标实现是否声明
            NamedDS named = method.getAnnotation(NamedDS.class);
            if (named == null) {
                // 查询接口定义是否声明
                named = methodSignature.getMethod().getAnnotation(NamedDS.class);
                if (named == null) {
                    obj = Boolean.FALSE;
                }
            }

            if (named != null) {
                obj = named;
            }
            namedMap.put(methodSignature.getMethod(), named);
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
