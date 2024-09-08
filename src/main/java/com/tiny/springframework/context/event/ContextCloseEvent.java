package com.tiny.springframework.context.event;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class ContextCloseEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextCloseEvent(Object source) {
        super(source);
    }
}
