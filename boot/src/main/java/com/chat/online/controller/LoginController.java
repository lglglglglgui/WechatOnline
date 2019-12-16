package com.chat.online.controller;
import com.chat.online.pojo.User;
import com.chat.online.service.LoginService;
import com.chat.online.utils.MD5Utils;
import com.chat.online.utils.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Resource
    private LoginService loginService;

    private String filehead = "/static/source/img/default_head.jpg";

    @RequestMapping("/login")
    public String userLogin(HttpSession session, Model model, String username, String paw, String role){
        if("0".equals(role)){
            paw = MD5Utils.buildMD5Code(paw);
            User user = loginService.userLogin(username,paw);
            if(user==null){
                model.addAttribute("user",user);
                model.addAttribute("message","用户名或者账号错误");
            }else{
                loginService.setLastTime(user.getUserId());
                session.setAttribute("user",user);
                return "redirect:/chat/index";
            }
        }
        return "login";
    }

    /*
    用户注册
     */
    @RequestMapping("/register")
    public String userRegister(HttpSession session,User user){
        user.setPaw(MD5Utils.buildMD5Code(user.getPaw()));
        user.setUserId(RandomUtils.createUserId());
        user.setFilehead(filehead);
        int result = loginService.registerUser(user);
        if(result!=0){
            session.setAttribute("user",user);
            return "redirect:/chat/index";
        }
       return "register";
    }

    /*
      判断用户名是否重复
     */
    @RequestMapping("/repeat")
    public @ResponseBody String reUsername(String username){
        int result = loginService.reUsername(username);
       if (result != 0){
           return "false";
       }else{
           return "ok";
       }
    }

    /*
    注销
     */
    @RequestMapping("/undo")
    public String undo(HttpSession session){
        session.invalidate();
        return "redirect:/view/login.jsp";
    }

}
