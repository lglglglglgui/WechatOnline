package com.chat.online.service.impl;
import com.chat.online.mapper.ChatMapper;
import com.chat.online.pojo.Room;
import com.chat.online.service.ChatService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    ChatMapper chatMapper;

    @Override
    public List<Room> getAllChatrooms() {
        return chatMapper.getAllChatrooms();
    }

    @Override
    public Room getoRoById(String roomid) {
        return chatMapper.selectOneRoom(roomid);
    }

    @Override
    public int isAddroom(String userId, String roomid) {
        return chatMapper.isAddroom(userId,roomid);
    }

    @Override
    public void addRoom(String userId, String roomid) {
        int number = chatMapper.selectRoomNumber(roomid);
        chatMapper.addRoom(userId,roomid,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString());
        chatMapper.addRoomNumber(roomid,++number);
    }
}
