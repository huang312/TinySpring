package com.tiny.springframework.event;

import com.tiny.springframework.context.ApplicationListener;
import com.tiny.springframework.context.event.ContextRefreshedEvent;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplication(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
