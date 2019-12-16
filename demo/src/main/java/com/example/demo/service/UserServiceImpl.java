package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 李归
 * @create 2019-01-17 15:04
 **/
@Service
public class UserServiceImpl implements  UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByName(Integer id) {
        return userMapper.findUserByName(id);
    }
}
