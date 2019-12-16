package com.chat.online.controller;
import com.chat.online.pojo.Room;
import com.chat.online.pojo.User;
import com.chat.online.service.ChatService;
import com.chat.online.websocket.ChatSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping("/index")
    public String getAllChatrooms(Model model){
        List<Room> list = chatService.getAllChatrooms();
        Map<String,Integer> map = ChatSocketServer.roomCount;
        model.addAttribute("list",list);
        model.addAttribute("Mymap",map);
        model.addAttribute("length",list.size());
        return "/view/index";
    }

    @RequestMapping("/room/{id}")
    public String addRoom(Model model, HttpSession session, @PathVariable("id") String roomid){
        User user = (User)session.getAttribute("user");
        String userId = user.getUserId();
        int number = chatService.isAddroom(userId,roomid);
        if (number == 0){
            //添加加入房间记录
            chatService.addRoom(userId,roomid);
        }
        Room room = chatService.getoRoById(roomid);
        model.addAttribute("room",room);
        return "/view/chat";
    }

}
