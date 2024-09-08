package com.tiny.springframework.context.event;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.context.ApplicationEvent;
import com.tiny.springframework.context.ApplicationListener;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public interface ApplicationEventMulticaster {

    /**
     * 增加一个监听器监听所有事件
     *
     * @param listener 监听器
     */
    void addApplicationListener(ApplicationListener<ApplicationEvent> listener);

    /**
     * 移除一个监听器
     *
     * @param listener 监听器
     */
    void removeApplicationListener(ApplicationListener<ApplicationEvent> listener);

    /**
     * 广播指定事件
     *
     * @param event 事件
     */
    void multicastEvent(ApplicationEvent event) throws BeansException;
}
