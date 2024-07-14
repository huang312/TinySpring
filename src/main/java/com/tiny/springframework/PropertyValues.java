package com.tiny.springframework;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录一个Bean的所有属性信息
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }
}
