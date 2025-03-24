package com.tiny.springframework.context.support;

import com.tiny.springframework.bean.factory.ConfigurableListableBeanFactory;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.config.BeanFactoryPostProcessor;
import com.tiny.springframework.bean.factory.config.BeanPostProcessor;
import com.tiny.springframework.context.ApplicationEvent;
import com.tiny.springframework.context.ApplicationListener;
import com.tiny.springframework.context.ConfigurableApplicationContext;
import com.tiny.springframework.context.event.ApplicationEventMulticaster;
import com.tiny.springframework.context.event.ContextCloseEvent;
import com.tiny.springframework.context.event.ContextRefreshedEvent;
import com.tiny.springframework.context.event.SimpleApplicationEventMulticaster;
import com.tiny.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;
    @Override
    public void refresh() throws BeansException {
        // 1.创建BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.添加ApplicationContextAwareProcessor，让继承ApplicationContextAware接口的Bean对象能够感知到ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5.BeanPostProcessor需要提前于其他Bean对象实例化之前进行注册
        registerBeanPostProcessors(beanFactory);

        // 6.初始化事件发布者
        initApplicationEventMulticaster();

        // 7.注册事件监听器
        registerListener();

        // 8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9.发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 初始化事件监听广播器
     */
    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     *
     * @throws BeansException
     */
    private void registerListener() throws BeansException {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void publishEvent(ApplicationEvent event) throws BeansException {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void finishRefresh() throws BeansException {
        publishEvent(new ContextRefreshedEvent(this));
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        try{
            // 发布容器关闭事件
            publishEvent(new ContextCloseEvent(this));
            // 销毁单例
            getBeanFactory().destroySingletons();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 beanFactory 中获取所有的beanFactoryPostProcessor并执行
     *
     * @param beanFactory
     * @throws BeansException
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException{
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

    }

    /**
     * 注册所有的BeanPostProcessor
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException{
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return getBeanFactory().getBean(name, clazz);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
