package com.tiny.springframework.config;

/**
 *
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass(){
        return this.beanClass;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }
}
