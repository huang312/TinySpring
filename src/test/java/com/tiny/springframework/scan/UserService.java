package com.tiny.springframework.scan;

import com.tiny.springframework.stereotype.Component;

/**
 * @Descrpition
 * @Date 2025/3/19
 */
@Component("userService")
public class UserService {
    public String queryInfo(String name) {
        System.out.println("query user info: " + name);
        return name;
    }
}
