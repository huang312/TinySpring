package com.tiny.springframework.event;

import com.tiny.springframework.context.ApplicationEvent;
import com.tiny.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Descrpition
 * @Date 2024/9/8
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplication(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
