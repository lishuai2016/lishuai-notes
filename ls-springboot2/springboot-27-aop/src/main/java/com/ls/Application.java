package com.ls;


import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2018-12-16 21:54
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
