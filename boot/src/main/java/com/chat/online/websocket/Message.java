package com.chat.online.websocket;
import com.chat.online.pojo.OnlineUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Message {
    /**
     * 消息类型
     */
    public static String NOTICE = "notice";     //通知
    public static String MESSAGE = "message";   //消息

    /**
     * 组装信息返回给前台
     * @param message  交互信息
     * @param type     信息类型
     * @param userList 在线列表
     * @return
     *
     * "massage" : {
     *              "from" : "xxx",
     *              "to" : "xxx",
     *              "content" : "xxx",
     *              "time" : "xxxx.xx.xx"
     *          },
     * "type" : {notice|message},
     * "list" : {[xx],[xx],[xx]}
     */
    public static String getMessage(String message,String type,Collection<OnlineUser> userList){
        JSONObject msg = new JSONObject();
        msg.put("message",message);
        msg.put("type", type);
        if(userList!=null && userList.size()>0){
            List<String> propertys = new ArrayList<String>();
            propertys.add("session");
            JSONArray userListArray = JSONArray.fromObject(userList,JsonConfigUtils.getJsonConfig(propertys));
            msg.put("list", userListArray);
        }
        return msg.toString();
    }

    /**
     * 消息内容
     * @param fromUser
     * @param to
     * @param content
     * @param time
     * @return
     *          {
     *              "from" : "xxx",
     *              "to" : "xxx",
     *              "content" : "xxx",
     *              "time" : "xxxx.xx.xx"
     *          }
     */
    public static String getContent(OnlineUser fromUser,String to,String content,String time){
        JSONObject contentJson = new JSONObject();

        // 转化为json串时去掉session，用户session不能被序列化
        List<String> propertys = new ArrayList<String>();
        propertys.add("session");
        contentJson.put("from",JSONObject.fromObject(fromUser,JsonConfigUtils.getJsonConfig(propertys)));

        contentJson.put("to",to);
        contentJson.put("content",content);
        contentJson.put("time",time);
        return contentJson.toString();
    }

    /**
     * 广播消息
     * @param message 消息
     * @param onlineUsers 在线用户
     */
    public static void broadcast(String message,Collection<OnlineUser> onlineUsers){
        for(OnlineUser user : onlineUsers){
            try {
                user.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 对特定用户发送消息
     * @param message
     * @param onlineUser
     */
    public static void singleSend(String message, OnlineUser onlineUser){
        try {
            onlineUser.getSession().getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
