package com.tiny.springframework.dependency;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Descrpition
 * @Date 2025/3/24
 */
public class ApiTest {
    @Test
    public void test() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dependency.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userDao.queryUserInfo("张三"));
        System.out.println(userService.query("李四"));
    }
}
