package com.chat.online.service;
import com.chat.online.pojo.User;

public interface LoginService {
    public User userLogin(String username, String paw);
    int registerUser(User user);
    public int reUsername(String username);
    void setLastTime(String userID);
}
