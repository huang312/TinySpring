package com.tiny.springframework.core.io;

import com.tiny.springframework.bean.exception.BeansException;

import java.io.*;

/**
 * 文件系统资源解析器
 */
public class FileSystemResource implements Resource{
    /**
     * 文件路径
     */
    private final String path;
    private File file;
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    public FileSystemResource(String path){
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getPath();
    }

    public String getPath() {
        return path;
    }
}
