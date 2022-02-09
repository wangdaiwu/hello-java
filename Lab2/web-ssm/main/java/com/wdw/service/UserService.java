package com.wdw.service;

import com.wdw.po.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public User findUser(User user);
    public User findUserByUsername(String username);
    public void deleteUserByUsername(String username);
    public List<User> getUserList();
}
