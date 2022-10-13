package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("statistics")
public class StatisticsBean {

    @JsonProperty("jsurl")
    @TableField("jsurl")
    private String jsUrl;

    public String getJsUrl() {
        return jsUrl;
    }

    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }
}
