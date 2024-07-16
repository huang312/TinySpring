package com.tiny.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认的资源加载器实现
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASS_PATH_PREFIX)){
            // 加载classpath下的资源文件
            return new ClassPathResource(location.substring(CLASS_PATH_PREFIX.length()));
        } else {
            try{ // 加载URL指定的资源文件
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 加载文件系统中的资源文件
                return new FileSystemResource(location);
            }
        }
    }
}
