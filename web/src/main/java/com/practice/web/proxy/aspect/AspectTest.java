package com.practice.web.proxy.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

    @Before("execution(* aspectTest(..))")
    public void test() {
        System.out.println("aop before");
    }
}
