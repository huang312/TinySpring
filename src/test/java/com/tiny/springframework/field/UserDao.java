package com.tiny.springframework.field;

import com.tiny.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
@Component
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小傅哥，北京，亦庄");
        hashMap.put("10002", "八杯水，上海，尖沙咀");
        hashMap.put("10003", "阿毛，天津，东丽区");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
