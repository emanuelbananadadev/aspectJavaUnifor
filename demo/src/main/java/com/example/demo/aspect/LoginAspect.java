package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Pointcut("within(com.example.demo.controller..*)")
    public void controllerMethod() {}

    @Pointcut("within(com.example.demo.service..*)")
    public void  serviceMethod() {}

    @Before("controllerMethod() || serviceMethod()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Iniciando execução de método: {} - arg: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "controllerMethod() || serviceMethod()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Método: {} executado com sucesso - Retorno: {}", joinPoint.getSignature().getName(), result );
    }

    @AfterThrowing(pointcut = "controllerMethod() || serviceMethod()", throwing = "throwable")
    public void logAfterThowing(JoinPoint joinPoint, Throwable throwable) {
        log.info("Exceção no método {} - Mensagem: {}", joinPoint.getSignature().getName(), throwable.getMessage());
    }




}
