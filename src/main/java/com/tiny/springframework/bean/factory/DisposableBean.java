package com.tiny.springframework.bean.factory;

/**
 * Bean实例销毁接口
 */
public interface DisposableBean {
    /**
     * Bean实例销毁时调用
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
