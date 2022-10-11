package com.example.cms9cc.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class AdminBean{
    @TableField("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "id='" + id + '\'' +
                '}';
    }
}
