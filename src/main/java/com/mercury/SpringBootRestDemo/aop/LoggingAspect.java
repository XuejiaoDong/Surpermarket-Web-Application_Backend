//package com.mercury.SpringBootRestDemo.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class LoggingAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Before("execution(* com.mercury.SpringBootRestDemo.controller.*.getAll(..))")
//    public void log() {
//        logger.info("Testing AOP LOG......");
//    }
//
//    @Around("execution(* com.mercury.SpringBootRestDemo.controller.*.getAll(..))")
//    public Object logAround(ProceedingJoinPoint pjp) {
//        logger.info("Testing AOP LOG Around - Before");
//        Object res = null;
//        try {
//            res = pjp.proceed();
//            System.out.println("hahahahaha");
//        } catch (Throwable throwable) {
//            logger.error("AOP exception", throwable);
//        }
//
//        logger.info("Testing AOP LOG Around - After");
//        return res;
//    }
//}
