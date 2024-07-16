package com.tiny.springframework.core.io;

import com.tiny.springframework.bean.exception.BeansException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL资源解析器
 */
public class UrlResource implements Resource{

    private final URL url;
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try{
            return urlConnection.getInputStream();
        } catch (IOException e){
            if (urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection) urlConnection).disconnect();
            }
            throw e;
        }
    }
    public UrlResource(URL url){
        this.url = url;
    }
}
