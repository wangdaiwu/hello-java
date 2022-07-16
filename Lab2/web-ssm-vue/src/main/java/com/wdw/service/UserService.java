package com.wdw.service;

import com.wdw.po.User;

import java.util.List;

public interface UserService {
    List<User> page(int current, int size);

    int count();

    User getOneByUsername(String username);

    int updateByUsername(User user);

    int save(User user);

    void removeByUsername(String username);
}
