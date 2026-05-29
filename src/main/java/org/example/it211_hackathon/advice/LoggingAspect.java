package org.example.it211_hackathon.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* org.example.it211_hackathon.service.PostService.create(..)) " +
            "|| execution(* org.example.it211_hackathon.service.PostService.update(..)) " +
            "|| execution(* org.example.it211_hackathon.service.PostService.patchUpdate(..))")
    public void postCreateOrUpdate() {
    }

    @Before("postCreateOrUpdate()")
    public void logBeforeCreateOrUpdate(JoinPoint joinPoint) {
        log.info("[AOP - BEFORE] Method Name: {}", joinPoint.getSignature().getName());
    }
}