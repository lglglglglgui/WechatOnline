package com.example.springboot.controller;
import com.example.springboot.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 李归
 * @create 2019-03-01 16:56
 **/
@Controller
public class UserController {
    @RequestMapping("/findAll")
    public String findAll(Model model){
       /* List<Users> list=userService.findAll();
        model.addAttribute("list",list);
        for (Users user:list){
            System.out.println(user);
        }*/
        List<Users> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            Users user=new Users();
            user.setId(i);
            user.setUsername("lglg"+i);
            user.setPaw("1346"+i);
            list.add(user);
        }

        model.addAttribute("list",list);
        model.addAttribute("msg","Hello LG");
        return "/findAll";
    }
}
