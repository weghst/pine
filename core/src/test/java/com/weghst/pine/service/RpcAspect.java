package com.weghst.pine.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-10-17 08:35
 */
public class RpcAspect {

    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-----------before-------" + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("-----------after");
        return result;
    }
}
