package com.tiny.springframework.context;

import java.util.EventListener;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplication(E event);
}
