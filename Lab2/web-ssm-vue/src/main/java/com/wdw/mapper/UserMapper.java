package com.wdw.mapper;

import com.wdw.po.User;

import java.util.List;

public interface UserMapper {
    List<User> page(int start, int size);

    int count();

    User getOneByUsername(String username);

    int updateByUsername(User user);

    int save(User user);

    void removeByUsername(String username);
}
