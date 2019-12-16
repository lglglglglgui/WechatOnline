package com.chat.online.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("S");
    /*
    随机成一个用户id
     */
    public static String createUserId(){
        StringBuilder sb = new StringBuilder("u");
        sb.append(sdf.format(new Date()).toString());
        for (int i = 0;i<2; i++){
            int num= (int)(Math.random()*990+100);
            sb.append(num);
        }
        return sb.toString();
    }
    /*
    随机生成一个房间号
     */
    public static String createRoomId(){
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(new Date()).toString());
        int num= (int)(Math.random()*990+100);
        sb.append(num);
        return sb.toString();
    }

}
