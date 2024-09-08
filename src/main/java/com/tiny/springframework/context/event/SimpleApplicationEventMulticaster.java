package com.tiny.springframework.context.event;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.BeanFactory;
import com.tiny.springframework.context.ApplicationEvent;
import com.tiny.springframework.context.ApplicationListener;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{
    @Override
    public void multicastEvent(ApplicationEvent event) throws BeansException {
        for (ApplicationListener<ApplicationEvent> listener : getApplicationListeners(event)) {
            listener.onApplication(event);
        }
    }
}
