package com.tiny.springframework;

import com.tiny.springframework.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_beanFactory(){
        // 1.创建 BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        // 2.创建 BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        // 3.向BeanFactory注册Bean
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 4.获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        // 5.测试结果
        userService.queryUserInfo();
    }
}
