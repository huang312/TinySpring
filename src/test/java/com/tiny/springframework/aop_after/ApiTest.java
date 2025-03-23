package com.tiny.springframework.aop_after;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
public class ApiTest {
    @Test
    public void test_aop_after() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-after.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
