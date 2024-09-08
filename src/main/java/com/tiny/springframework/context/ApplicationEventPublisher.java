package com.tiny.springframework.context;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event) throws BeansException;
}
