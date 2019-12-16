package com.chat.online.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name="userId")
    private String userId;
    private String username;
    private String paw;
    private String nickname;
    private String filehead;
    private String autograph;
    @Column(name="lastTime")
    private String lastTime;
    @Column(name="forbidTime")
    private Date forbidTime;
    private String phone;
    private int status;
}
