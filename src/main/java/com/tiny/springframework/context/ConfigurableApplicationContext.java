package com.tiny.springframework.context;

import com.tiny.springframework.bean.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
