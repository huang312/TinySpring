package com.tiny.springframework.context;

import com.tiny.springframework.bean.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 向JVM注册关闭钩子
     */
    void registerShutdownHook();

    /**
     * 关闭容器
     */
    void close();
}
