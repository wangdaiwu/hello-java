package com.wdw.mapper;

import com.wdw.po.User;

public interface UserMapper {
    public void addUser(User user);
    public User findUser(User user);
    public User findUserByUsername(String username);
}
