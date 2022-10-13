package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("js_ad")
public class JsAdBean {
    private String jsLink;

    public String getJsLink() {
        return jsLink;
    }

    public void setJsLink(String jsLink) {
        this.jsLink = jsLink;
    }
}
