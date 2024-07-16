package com.tiny.springframework.core.io;

import com.tiny.springframework.utils.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取classpath下的资源
 */
public class ClassPathResource implements Resource{
    /**
     * 资源路径 相对于classpath
     */
    private final String path;

    private ClassLoader classLoader;
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if(resourceAsStream == null){
            throw new FileNotFoundException(this.path + "can not be opened because it does not exist");
        }
        return resourceAsStream;
    }

    public ClassPathResource(String path){
        this(path, null);
    }
    public ClassPathResource(String path, ClassLoader classLoader){
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader());
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public String getPath() {
        return path;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
