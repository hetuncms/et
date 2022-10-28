package com.example.cms9cc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"t", "a", "o"}, allowSetters = true)
public class LiveBean {

    private int status;
    private String info;
    private List<Live_item> live_item;
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setLive_item(List<Live_item> live_item) {
        this.live_item = live_item;
    }
    public List<Live_item> getLive_item() {
        return live_item;
    }
    public class Live_item {

        private long id;
        private String playid;
        private int gameType;
        private String iframeLink;
        private String title;
        private Date date;
        private String isTop;
        private String leftName;
        private String rightName;
        private String leftImg;
        private String rightImg;
        private String gameName;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setPlayid(String playid) {
            this.playid = playid;
        }
        public String getPlayid() {
            return playid;
        }

        public void setGameType(int gameType) {
            this.gameType = gameType;
        }
        public int getGameType() {
            return gameType;
        }

        public void setIframeLink(String iframeLink) {
            this.iframeLink = iframeLink;
        }
        public String getIframeLink() {
            return iframeLink;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setDate(Date date) {
            this.date = date;
        }
        public Date getDate() {
            return date;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }
        public String getIsTop() {
            return isTop;
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName;
        }
        public String getLeftName() {
            return leftName;
        }

        public void setRightName(String rightName) {
            this.rightName = rightName;
        }
        public String getRightName() {
            return rightName;
        }

        public void setLeftImg(String leftImg) {
            this.leftImg = leftImg;
        }
        public String getLeftImg() {
            return leftImg;
        }

        public void setRightImg(String rightImg) {
            this.rightImg = rightImg;
        }
        public String getRightImg() {
            return rightImg;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }
        public String getGameName() {
            return gameName;
        }

    }

}