package com.chat.online.mapper;

import com.chat.online.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface LoginMapper extends Mapper <User> {
        @Select("select count(*) from user where username = #{username}")
        public int reNameUser(String username);

        @Update("update user set lastTime = #{lastTime}  where userId = #{userid} ")
        public void setLastTime (@Param("userid")String userid,@Param("lastTime")String lastTime);
}
