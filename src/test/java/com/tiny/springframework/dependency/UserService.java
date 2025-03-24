package com.tiny.springframework.dependency;

import com.tiny.springframework.bean.factory.annotation.Autowired;
import com.tiny.springframework.stereotype.Component;

/**
 * @Descrpition
 * @Date 2025/3/24
 */
@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    public String query(String user) {
        return userDao.queryUserInfo(user);
    }
}
