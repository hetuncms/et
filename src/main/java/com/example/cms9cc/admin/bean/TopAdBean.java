package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("top_ad")
public class TopAdBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sort;
    private String url;
    private String pic_url;
    private Integer pic_width;
    private Integer pic_height;
    private String status;
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Integer getPic_width() {
        return pic_width;
    }

    public void setPic_width(Integer pic_width) {
        this.pic_width = pic_width;
    }

    public Integer getPic_height() {
        return pic_height;
    }

    public void setPic_height(Integer pic_height) {
        this.pic_height = pic_height;
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
