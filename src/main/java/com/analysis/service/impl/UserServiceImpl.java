package com.analysis.service.impl;

import com.analysis.mapper.UserMapper;
import com.analysis.pojo.User;
import com.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
