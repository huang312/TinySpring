package com.tiny.springframework.event;

import com.tiny.springframework.context.ApplicationListener;
import com.tiny.springframework.context.event.ContextCloseEvent;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class ContextClosedEventListener implements ApplicationListener<ContextCloseEvent> {
    @Override
    public void onApplication(ContextCloseEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
