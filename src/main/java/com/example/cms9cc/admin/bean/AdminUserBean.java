package com.example.cms9cc.admin.bean;

public class AdminUserBean
{
    private String name;

    private String avatar;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getAvatar(){
        return this.avatar;
    }
}
