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
    public List<User> page(int current, int size) {
        return userMapper.page((current - 1) * size, size);
    }

    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public User getOneByUsername(String username) {
        return userMapper.getOneByUsername(username);
    }

    @Override
    public int updateByUsername(User user) {
        return userMapper.updateByUsername(user);
    }

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public void removeByUsername(String username) {
        userMapper.removeByUsername(username);
    }
}
