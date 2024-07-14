package com.tiny.springframework;

import com.tiny.springframework.bean.UserService;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionTest {

    @Test
    public void testConstructor() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("com.tiny.springframework.bean.UserService");
        String name = "jack";
        Object arg = name;
        System.out.println(arg.getClass());
        Constructor declaredConstructor = clazz.getDeclaredConstructor(arg.getClass());
        UserService userService = (UserService) declaredConstructor.newInstance(arg);
        userService.queryUserInfo();
    }
}
