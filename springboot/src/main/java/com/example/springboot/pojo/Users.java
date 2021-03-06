package com.example.springboot.pojo;

/**
 * @author 李归
 * @create 2019-03-04 21:03
 **/
public class Users {

    private Integer id;
    private String username;
    private String paw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPaw() {
        return paw;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", paw='" + paw + '\'' +
                '}';
    }



}
