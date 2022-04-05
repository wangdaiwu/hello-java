package com.wdw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdw.mapper.UserMapper;
import com.wdw.entity.User;
import com.wdw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
