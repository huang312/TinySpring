package com.tiny.springframework.core.io;

import com.tiny.springframework.bean.exception.BeansException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 代表资源
 */
public interface Resource {
    /**
     * 获取资源的输入流
     * @return
     */
    InputStream getInputStream() throws IOException;
}
