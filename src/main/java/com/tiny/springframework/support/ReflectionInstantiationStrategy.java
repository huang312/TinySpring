package com.tiny.springframework.support;

import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

import java.lang.reflect.InvocationTargetException;

public class ReflectionInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try{
            if(args == null || args.length == 0){
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                Class[] parameters = new Class[args.length];
                for(int i = 0; i < parameters.length; i++){
                    parameters[i] = args[i].getClass();
                }
                return clazz.getDeclaredConstructor(parameters).newInstance(args);
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate bean [" + clazz.getName() +"]", e);
        }
    }
}
