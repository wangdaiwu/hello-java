package com.wdw.service;

import com.wdw.po.User;

public interface UserService {
    public void addUser(User user);
    public User findUser(User user);
    public User findUserByUsername(String username);
}
