package com.tiny.springframework.exception;

/**
 * Bean 相关的异常
 */
public class BeansException extends Exception{
    public BeansException(){}

    public BeansException(String s){
        super(s);
    }

    public BeansException(String s, Exception e){
        super(s, e);
    }
}
