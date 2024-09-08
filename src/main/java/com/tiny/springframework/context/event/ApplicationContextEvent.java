package com.tiny.springframework.context.event;

import com.tiny.springframework.context.ApplicationContext;
import com.tiny.springframework.context.ApplicationEvent;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
