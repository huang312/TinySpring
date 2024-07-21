package com.tiny.springframework.bean.factory.config;

/**
 * 用于引用类型的属性
 */
public class BeanReference {
    private String beanName;

    public BeanReference(){}

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
