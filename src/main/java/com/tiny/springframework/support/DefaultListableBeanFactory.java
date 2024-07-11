package com.tiny.springframework.support;

import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的 BeanFactory 实现类，实现了BeanFactory的完整功能
 * 包括 BeanDefinition 注册器，Bean 注册器以及 Bean 的获取功能
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    // 保存 BeanDefinition
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null) throw new BeansException("No bean named '" + name + "'");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
