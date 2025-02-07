package com.Transaction.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.Transaction.service.TransactionService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.Transaction.service.TransactionService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method executed successfully: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "execution(* com.Transaction.service.TransactionService.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName());
        System.out.println("Exception message: " + exception.getMessage());
    }

    @Around("execution(* com.Transaction.service.TransactionService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time of " + joinPoint.getSignature().getName() + " : " + (endTime - startTime) + "ms");
        return result;
    }
}


