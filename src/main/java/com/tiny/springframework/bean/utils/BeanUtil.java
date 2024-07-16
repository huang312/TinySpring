package com.tiny.springframework.bean.utils;

import com.tiny.springframework.bean.exception.BeansException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Bean工具类
 */
public class BeanUtil {

    /**
     * 设置对象中某个属性的值
     * 要求该对象包含set方法
     * @param bean
     * @param value
     * @param name
     * @throws BeansException
     */
    public static void setField(Object bean, Object value, String name) throws BeansException {
        try {
            // 获取Bean实例的类型
            Class<?> clazz = bean.getClass();
            // 获取name对应的属性
            Field field = clazz.getDeclaredField(name);
            Class<?> fieldType = field.getType();
            // 获取set方法的名称
            String fieldSetMethodName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
            // 获取对应的属性set方法
            Method fieldSetMethod = clazz.getDeclaredMethod(fieldSetMethodName, fieldType);
            // 执行属性的set方法 将value强转为fieldType类型
            fieldSetMethod.invoke(bean, fieldType.cast(value));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            throw new BeansException("Set bean [" + bean.getClass().getName() + "] property ["+name+"] failed.", e);
        }
    }
}
