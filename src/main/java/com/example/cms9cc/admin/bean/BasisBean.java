package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("basis")
public class BasisBean {

    @JsonProperty("current_template")
    @TableField("current_template")
    private String currentTemplate;
    @TableField(exist = false)
    private String[] templates;
    @JsonProperty("site_name")
    @TableField("site_name")
    private String siteName;
    @TableField("key_words")
    @JsonProperty("key_words")
    private String keyWords;
    @TableField("describe")
    private String describe;
    @TableField("announcement")
    private String announcement;
    @TableField("logo")
    private String logo;
    @TableField("email")
    private String email;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String[] getTemplates() {
        return templates;
    }

    public void setTemplates(String[] templates) {
        this.templates = templates;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentTemplate() {
        return currentTemplate;
    }

    public void setCurrentTemplate(String currentTemplate) {
        this.currentTemplate = currentTemplate;
    }
}
