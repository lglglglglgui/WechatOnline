package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by ASUS on 2019/1/17.
 */

@Mapper
public interface UserMapper {
    public User findUserByName(Integer id);
}
