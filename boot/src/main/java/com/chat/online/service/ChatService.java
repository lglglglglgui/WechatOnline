package com.chat.online.service;

import com.chat.online.pojo.Room;

import java.util.List;

public interface ChatService {
    List<Room> getAllChatrooms();

    Room getoRoById(String roomid);

    int isAddroom(String userId, String roomid);

    void addRoom(String userId, String roomid);
}
