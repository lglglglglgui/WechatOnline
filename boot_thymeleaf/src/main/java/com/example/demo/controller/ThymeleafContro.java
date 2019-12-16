package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author ภ๎น้
 * @create 2019-03-04 20:32
 **/
@Controller
public class ThymeleafContro {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","Hello LG");
        return "index";
    }
}
