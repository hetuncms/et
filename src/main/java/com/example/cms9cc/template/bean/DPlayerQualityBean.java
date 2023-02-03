package com.example.cms9cc.template.bean;

public class DPlayerQualityBean {
    private String name;
    private String url;
    private String type;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name:\'" + name + '\'' +
                ", url:'" + url + '\'' +
                ", type:'" + type + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
