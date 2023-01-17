package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("admin_user")
public class LoginBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof LoginBean))
            return false;
        LoginBean loginBean = (LoginBean) o;
        if (!getUsername().equals(loginBean.getUsername())) return false;
        return getPassword().equals(loginBean.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
