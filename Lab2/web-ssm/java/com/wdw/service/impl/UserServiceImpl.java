package com.wdw.service.impl;

import com.wdw.mapper.UserMapper;
import com.wdw.po.User;
import com.wdw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User findUser(User user) {
        user = userMapper.findUser(user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        return user;
    }

    @Override
    public void deleteUserByUsername(String username) {
        userMapper.deleteUserByUsername(username);
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userMapper.getUserList();
        return userList;
    }
}
