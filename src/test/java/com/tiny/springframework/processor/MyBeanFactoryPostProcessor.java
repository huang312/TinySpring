package com.tiny.springframework.processor;

import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.PropertyValues;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.ConfigurableListableBeanFactory;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userService = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "字节跳动"));
    }
}
