package com.example.cms9cc;

import java.util.Date;
import java.util.List;

public class LiveBean {
    private int status;
    private String info;
    private List<LiveItem> live_item;
    private Long code;

    public LiveBean() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

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

    public static class LiveItem {

        private long id;
        private String playid;
        private int liveType;
        private String iframeLink;
        private List<Integer> visitingScore;
        private List<Integer> mainScore;
        private Boolean liveing;
        private Long matchStartTime;
        private String title;
        private Date date;
        private String liveId;
        private Integer hasOdds;
        private List<Integer> leftTeamScore;
        private List<Integer> rightTeamScore;
        private Boolean isTop;
        private String leftName;
        private String rightName;
        private String leftImg;
        private String rightImg;
        private String gameName;
        private String matchId;
        private Integer liveStatus;
        private Boolean isHot;

        public List<Integer> getVisitingScore() {
            return visitingScore;
        }

        public void setVisitingScore(List<Integer> visitingScore) {
            this.visitingScore = visitingScore;
        }

        public List<Integer> getMainScore() {
            return mainScore;
        }

        public void setMainScore(List<Integer> mainScore) {
            this.mainScore = mainScore;
        }

        public Integer getHasOdds() {
            return hasOdds;
        }

        public void setHasOdds(Integer hasOdds) {
            this.hasOdds = hasOdds;
        }

        public List<Integer> getLeftTeamScore() {
            return leftTeamScore;
        }

        public void setLeftTeamScore(List<Integer> leftTeamScore) {
            this.leftTeamScore = leftTeamScore;
        }

        public List<Integer> getRightTeamScore() {
            return rightTeamScore;
        }

        public void setRightTeamScore(List<Integer> rightTeamScore) {
            this.rightTeamScore = rightTeamScore;
        }

        public Boolean getLiveing() {
            return liveing;
        }

        public void setLiveing(Boolean liveing) {
            this.liveing = liveing;
        }

        public Boolean getTop() {
            return isTop;
        }

        public void setTop(Boolean top) {
            isTop = top;
        }

        public Boolean getHot() {
            return isHot;
        }

        public void setHot(Boolean hot) {
            isHot = hot;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public Integer getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(Integer liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public Long getMatchStartTime() {
            return matchStartTime;
        }

        public void setMatchStartTime(Long matchStartTime) {
            this.matchStartTime = matchStartTime;
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
            return new Date(this.getMatchStartTime() * 1000);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Boolean isTop() {
            return isTop;
        }

        public void setIsTop(Boolean isTop) {
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
