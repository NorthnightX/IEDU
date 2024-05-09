package com.iedu.service.aspect;

import com.iedu.common.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

//@Component
//@Aspect
//public class CommonAspect {
//
//    @Before("execution(* com.iedu.service.service..insert*(..)) || execution(* com.iedu.service.service..update*(..)) && args(entity)")
//    public void beforeSaveOrUpdate(JoinPoint joinPoint, Object entity) {
//
//    }
//
//}
