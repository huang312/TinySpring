package com.tiny.springframework;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean 工厂，提供 Bean 的注册和获取功能
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beandefinition) {
        beanDefinitionMap.put(name, beandefinition);
    }
}
