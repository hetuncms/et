package com.example.cms9cc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"t", "a", "o"}, allowSetters = true)
public class LiveBean {

    private int status;
    private String info;
    private List<LiveItem> live_item;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<LiveItem> getLive_item() {
        return live_item;
    }

    public void setLive_item(List<LiveItem> live_item) {
        this.live_item = live_item;
    }

    public class LiveItem {

        private long id;
        private String playid;
        private int liveType;
        private String iframeLink;

        private Long longTime;
        private String title;
        private Date date;

        private String liveId;

        private Boolean isTop;
        private String leftName;
        private String rightName;
        private String leftImg;
        private String rightImg;
        private String gameName;

        private String matchId;

        private Boolean liveStatus;

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public Boolean getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(Boolean liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public Long getLongTime() {
            return longTime;
        }

        public void setLongTime(Long longTime) {
            this.longTime = longTime;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPlayid() {
            return playid;
        }

        public void setPlayid(String playid) {
            this.playid = playid;
        }

        public int getLiveType() {
            return liveType;
        }

        public void setLiveType(int liveType) {
            this.liveType = liveType;
        }

        public String getIframeLink() {
            return iframeLink;
        }

        public void setIframeLink(String iframeLink) {
            this.iframeLink = iframeLink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getDate() {
            return new Date(this.getLongTime());
        }

        public Boolean getTop() {
            return isTop;
        }

        public void setTop(Boolean isTop) {
            this.isTop = isTop;
        }

        public String getLeftName() {
            return leftName;
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName;
        }

        public String getRightName() {
            return rightName;
        }

        public void setRightName(String rightName) {
            this.rightName = rightName;
        }

        public String getLeftImg() {
            return leftImg;
        }

        public void setLeftImg(String leftImg) {
            this.leftImg = leftImg;
        }

        public String getRightImg() {
            return rightImg;
        }

        public void setRightImg(String rightImg) {
            this.rightImg = rightImg;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

    }

}
