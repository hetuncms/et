package com.example.cms9cc.admin.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "couplets_ad")
public class CoupletsBean {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private String location;
    private String url;
    private String picUrl;
    private String status;
    private String tel;
    @Transient
    private boolean update;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
