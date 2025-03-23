package com.tiny.springframework.bean.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
