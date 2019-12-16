package com.chat.online.mapper;
import com.chat.online.pojo.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface ChatMapper extends Mapper<Room>{

    @Select("select * from room where status = 1")
    public List<Room> getAllChatrooms();

    @Select("select count(*) from addroom where userid = #{userId} and roomid = #{roomId}")
    public int isAddroom(@Param("userId") String userId,@Param("roomId") String roomId);

    @Insert("insert into addroom(userid,roomid,time) values(#{userId},#{roomId},#{date})")
    void addRoom(@Param("userId")String userId,@Param("roomId") String roomid,@Param("date") String s);

    @Select("select * from room where roomid = #{roomid}")
    Room selectOneRoom(@Param("roomid")String roomid);
    //查询房间的人数数量
    @Select("select number from room where roomid = #{roomid}")
    int selectRoomNumber(@Param("roomid")String roomid);
    //更改房间人数数量
    @Update("update room set number = #{number} where roomid = #{roomid}")
    void addRoomNumber(@Param("roomid")String roomid,@Param("number") int i);
}
