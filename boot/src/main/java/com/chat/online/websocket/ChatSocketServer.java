package com.chat.online.websocket;
import com.chat.online.pojo.OnlineUser;
import com.chat.online.pojo.User;
import com.chat.online.utils.CommonDateUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/websocket/{roomid}/{userid}" ,configurator=HttpSessionConfigurator.class)
public class ChatSocketServer {
    //统计每个房间的人数人数  房间id,  人数
    public static Map<String, Integer> roomCount = new ConcurrentHashMap<String,Integer>();
    //每个聊天室列表     房间id     用户id  用户对象{用户id,用户昵称,用户session}
    private static Map<String,Map<String,OnlineUser>> map = new ConcurrentHashMap<String,Map<String,OnlineUser>>();

    //打开连接
    @OnOpen
    public void onOpen(@PathParam("roomid") String roomid,@PathParam("userid") String userid , Session session,EndpointConfig config) {
        //房间人数加一
        if(roomCount.containsKey(roomid)){
            roomCount.put(roomid,roomCount.get(roomid)+1);
        }else {
            roomCount.put(roomid,1);
        }
        //获取当前用户的session
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        User user = (User)httpSession.getAttribute("user");
        OnlineUser onlineUser = new OnlineUser(userid,user.getNickname(),session);
        //将用户保存在房间列表中
        if(map.containsKey(roomid)){
            //添加进房间列表
            map.get(roomid).put(userid,onlineUser);
        }else{
            //不存在这样的房间,创建房间
            Map<String,OnlineUser> temp = new HashMap<String,OnlineUser>();
            temp.put(userid,onlineUser);
            map.put(roomid,temp);
        }
        //进行公告
        String content = "["+ CommonDateUtils.getDate()+":"+user.getNickname()+"加入聊天室,当前人数在线人数为"+roomCount.get(roomid)+"位";
        JSONObject msg = new JSONObject();
        msg.put("content",content);
        String message = Message.getMessage(msg.toString(),Message.NOTICE,map.get(roomid).values());
        Message.broadcast(message,map.get(roomid).values());

    }

    /*
    关闭连接
     */

    @OnClose
    public void onClose(@PathParam("roomid") String roomid,@PathParam("userid") String userid){
        Map<String,OnlineUser> myMap = map.get(roomid);
       //获取当前用户对象
        OnlineUser user = myMap.get(userid);
        //房间人数减一
        roomCount.put(roomid,roomCount.get(roomid)-1);
        //从房间列表中移除该用户
        myMap.remove(userid);

        //通知所有用户当前用户下线
        String content = "["+ CommonDateUtils.getDate()+":"+user.getNickName()+"离开聊天室,当前人数在线人数为"+roomCount.get(roomid)+"位";
        JSONObject msg = new JSONObject();
        msg.put("content",content);
        if(map.get(roomid).size()>0){
            String message = Message.getMessage(msg.toString(),Message.NOTICE,myMap.values());
            Message.broadcast(message,myMap.values());
        }

    }

    /*
        服务端接受消息
     */
    @OnMessage
    public void onMessage(@PathParam("roomid") String roomid,@PathParam("userid") String userid,String data){
        //获取当前房间Map
        Map<String,OnlineUser> myMap = map.get(roomid);
        JSONObject jsonObject = JSONObject.fromObject(data);
        JSONObject message = jsonObject.optJSONObject("message");
        String to = message.optString("to");
        String from = message.optString("from");
        //用户id
        if(!to.equals("")&&to != null)
        to = this.userIdCastNickNamer(to,roomid);
        //得到发起用户
        OnlineUser onlineUser =  myMap.get(userid);
        //进行context封装
        String sendContext = Message.getContent(onlineUser,to,message.optString("content"),message.optString("time"));
        String returnDate = Message.getMessage(sendContext,Message.MESSAGE,null);
        //判断目的用户
        if(to == null || to.equals("")){
            //发送给列表所有用户
            Message.broadcast(returnDate.toString(),myMap.values());
        }else{
            Message.singleSend(returnDate.toString(),myMap.get(from));
            //在获取一次用户id
            String[] useridList = message.optString("to").split(",");
            //发送给指定用户
            for (String id : useridList){
                if(!id.equals(from)){
                    Message.singleSend(returnDate.toString(),myMap.get(id));
                }
            }
        }

    }
    //发送错误
    @OnError
    public void onError  (Session session ,Throwable error){
            error.getStackTrace();
    }

    /*
        用户id转换成用户昵称
     */
    private String userIdCastNickNamer(String userIds,String roomid){
        StringBuffer sb = new StringBuffer();
        if(userIds != null && ! userIds.equals("")){
            String[] useridList = userIds.split(",");
            for (String s : useridList){
                if (!"".equals(s))
                sb = sb.append(map.get(roomid).get(s).getNickName()).append(",");
            }
        }
        return sb.substring(0,sb.length() - 1);
    }

}
