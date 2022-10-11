package com.example.cms9cc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CmsPlayBean {
    @JsonProperty("d_id")
    private int id;
    @JsonProperty("d_type")
    private int type;
    @JsonProperty("d_name")
    private String name;
    @JsonProperty("d_pic")
    private String pic;
    @JsonProperty("d_playurl")
    private String playUrl;
    @JsonProperty("d_time")
    private String date;

    public CmsPlayBean(int id, int type, String name, String pic, String playUrl, String date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pic = pic;
        this.playUrl = playUrl;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
