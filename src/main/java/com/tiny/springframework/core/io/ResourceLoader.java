package com.tiny.springframework.core.io;

/**
 * 资源加载器
 */
public interface ResourceLoader {
    String CLASS_PATH_PREFIX = "classpath:";
    Resource getResource(String location);
}
