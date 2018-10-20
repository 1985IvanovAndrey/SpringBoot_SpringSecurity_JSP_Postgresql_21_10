package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserInfo;

import java.util.List;

public interface UserService {
    void save(UserInfo userInfo);

    UserInfo findByUsername(String username);

    List<UserInfo> userList();

    void delUser(long id);
}
