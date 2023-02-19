package com.example.cms9cc.admin.bean;

import jakarta.persistence.*;

@Entity
@Table(name="float_ad")
public class FloatBean {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    private String picUrl;
    private String status;
    private String tel;
    @Transient
    private boolean update;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
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
