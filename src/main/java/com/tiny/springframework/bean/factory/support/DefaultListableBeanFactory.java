package com.tiny.springframework.bean.factory.support;

import com.tiny.springframework.bean.factory.ConfigurableListableBeanFactory;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.exception.BeansException;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认的 BeanFactory 实现类，实现了BeanFactory的完整功能
 * 包括 BeanDefinition 注册器，Bean 注册器以及 Bean 的获取功能
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    // 保存 BeanDefinition
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null) throw new BeansException("No bean named '" + name + "'");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        for (String beanName : beanDefinitionMap.keySet()) {
            getBean(beanName);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        for (String beanName : beanDefinitionMap.keySet()) {
            Class beanClass = beanDefinitionMap.get(beanName).getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        }
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }

        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeansException(requiredType + " expected single bean but foung " + beanNames.size());
    }
}
