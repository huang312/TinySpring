package com.tiny.springframework.utils;

public class ClassUtil {
    public static ClassLoader getDefaultClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
}
