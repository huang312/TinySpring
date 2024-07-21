package com.tiny.springframework.bean.factory.support;

import com.tiny.springframework.bean.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的 Bean 单例注册器实现
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 实例化的 Bean 容器
     */
    private Map<String, Object> singletonBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonBeanMap.get(name);
    }

    /**
     * 添加新的单例 Bean 实例，只有容器可以实例化 Bean, 所以访问权限设置为 protect
     * @param beanName Bean 名称
     * @param singletonObject 单例的 Bean 实例
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        this.singletonBeanMap.put(beanName, singletonObject);
    }
}
