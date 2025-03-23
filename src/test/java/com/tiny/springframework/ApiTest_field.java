package com.tiny.springframework;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.context.support.ClassPathXmlApplicationContext;
import com.tiny.springframework.field.IUserService;
import org.junit.Test;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
public class ApiTest_field {

    @Test
    public void test_scan() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-field.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
