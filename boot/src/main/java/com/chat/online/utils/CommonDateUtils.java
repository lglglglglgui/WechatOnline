package com.chat.online.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDateUtils {
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return sdf.format(new Date());
    }
}
