package com.tiny.springframework.bean.factory.annotation;

import java.lang.annotation.*;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Qualifier {
    String value() default "";
}
