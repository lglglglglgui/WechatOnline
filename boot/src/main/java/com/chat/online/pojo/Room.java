package com.chat.online.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String roomid;
    private String roomname;
    private String explain;
    private String notice;
    private int status;
    private int number;
    private String userid;
    private String roomheadfile;

}
