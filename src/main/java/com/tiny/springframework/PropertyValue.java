package com.tiny.springframework;

/**
 * Bean 的属性表示
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }
    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
