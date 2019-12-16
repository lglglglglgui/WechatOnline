package com.chat.online.service.impl;

import com.chat.online.mapper.LoginMapper;
import com.chat.online.pojo.User;
import com.chat.online.service.LoginService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper mapper;

    /*
    用户登录
     */
    @Override
    public User userLogin(String username, String paw) {
        Example example = new Example(User.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("username", username);
        c.andEqualTo("paw", paw);
        return mapper.selectOneByExample(example);
    }

    /*
        注册用户
     */
    @Override
    public int registerUser(User user) {
        user.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString());
        return mapper.insert(user);
    }

    /*
        判断用户名是否重复
     */

    @Override
    public int reUsername(String username) {
        return mapper.reNameUser(username);
    }

    /*
        添加最后登录时间
     */
    @Override
    public void setLastTime(String userID) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString();
        mapper.setLastTime(userID, time);
    }

}
