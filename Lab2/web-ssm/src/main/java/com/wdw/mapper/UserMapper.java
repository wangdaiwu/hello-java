package com.wdw.mapper;

import com.wdw.po.User;

import java.util.List;

public interface UserMapper {
    public void addUser(User user);
    public User findUser(User user);
    public User findUserByUsername(String username);
    public void deleteUserByUsername(String username);
    public List<User> getUserList();
}
