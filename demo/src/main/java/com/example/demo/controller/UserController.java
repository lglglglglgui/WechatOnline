package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李归
 * @create 2019-01-17 13:56
 **/
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "Hello World Hello World";
    }

    @RequestMapping("/findUserByName")
    public Object findUserByName(Model model,Integer id){
        User user=userService.findUserByName(id);
        return user;
    }
}
