package com.tiny.springframework.bean.config;

import com.tiny.springframework.bean.AutowireCapableBeanFactory;
import com.tiny.springframework.bean.support.AbstractBeanFactory;
import com.tiny.springframework.bean.support.InstantiationStrategy;
import com.tiny.springframework.bean.support.ReflectionInstantiationStrategy;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new ReflectionInstantiationStrategy();


}
