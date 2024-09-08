package com.tiny.springframework.context.event;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.BeanFactory;
import com.tiny.springframework.bean.factory.BeanFactoryAware;
import com.tiny.springframework.context.ApplicationEvent;
import com.tiny.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener<ApplicationEvent>> getApplicationListeners(ApplicationEvent event) throws BeansException {
        LinkedList<ApplicationListener<ApplicationEvent>> applicationListenerList = new LinkedList<ApplicationListener<ApplicationEvent>>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                applicationListenerList.add(listener);
            }
        }
        return applicationListenerList;
    }

    /**
     * 监听器是否监听指定事件
     *
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) throws BeansException {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        Type applicationListenerType = listenerClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) applicationListenerType).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        return eventClass.isAssignableFrom(event.getClass());
    }
}
