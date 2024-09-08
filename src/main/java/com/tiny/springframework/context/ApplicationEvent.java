package com.tiny.springframework.context;

import java.util.EventObject;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
