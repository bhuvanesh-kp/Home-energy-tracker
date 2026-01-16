package com.bhuvanesh.user_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut(value = "execution(* com.bhuvanesh.user_service.service.*.*(..))")
    public void ServicePointCut() {
    }

    @Before(value = "ServicePointCut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method called {} with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "ServicePointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method Completed {} with result {}", joinPoint.getSignature().getName(), result);
    }
}
