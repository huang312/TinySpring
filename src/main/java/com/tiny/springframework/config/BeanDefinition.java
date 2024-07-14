package com.tiny.springframework.config;

import com.tiny.springframework.PropertyValues;

/**
 * BeanDefinition
 */
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass(){
        return this.beanClass;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
