package com.tiny.springframework.bean.support;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.core.io.Resource;
import com.tiny.springframework.core.io.ResourceLoader;


public interface BeanDefinitionReader {
    /**
     * 获取容器的BeanDefinition注册器
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 从资源对象加载BeanDefinition
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinition(Resource resource) throws BeansException;

    /**
     * 从多个资源对象加载BeanDefinition
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinition(Resource... resources) throws BeansException;

    /**
     * 从某个位置的资源加载BeanDefinition
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinition(String location) throws BeansException;

    /**
     * 根据路径加载资源
     *
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
