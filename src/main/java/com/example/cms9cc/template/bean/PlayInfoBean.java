package com.example.cms9cc.template.bean;

import com.example.cms9cc.LiveBean;

import java.util.List;

public class PlayInfoBean {

    private Integer code;
    private String msg;
    private LiveBean.LiveItem data;
    private Result LiveInfoBean;
    private List<RateOddsItem> oddsItem;
    private MatchLeague footballLeague;

    public MatchLeague getFootballLeague() {
        return footballLeague;
    }

    public void setFootballLeague(MatchLeague footballLeague) {
        this.footballLeague = footballLeague;
    }

    public List<RateOddsItem> getOddsItem() {
        return oddsItem;
    }

    public void setOddsItem(List<RateOddsItem> oddsItem) {
        this.oddsItem = oddsItem;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LiveBean.LiveItem getData() {
        return data;
    }

    public void setData(LiveBean.LiveItem data) {
        this.data = data;
    }

    public Result getLiveInfoBean() {
        return LiveInfoBean;
    }

    public void setLiveInfoBean(Result liveInfoBean) {
        LiveInfoBean = liveInfoBean;
    }

    public static class Result {
        private long match_id;
        private List<LiveStreams> live_streams;

        public long getMatch_id() {
            return match_id;
        }

        public void setMatch_id(long match_id) {
            this.match_id = match_id;
        }

        public List<LiveStreams> getLive_streams() {
            return live_streams;
        }

        public void setLive_streams(List<LiveStreams> live_streams) {
            this.live_streams = live_streams;
        }

    }

    public static class LiveStreams {

        private String stream_id;
        private int type;
        private String url;
        private int format;
        private int source;
        private int status;

        public String getStream_id() {
            return stream_id;
        }

        public void setStream_id(String stream_id) {
            this.stream_id = stream_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getFormat() {
            return format;
        }

        public void setFormat(int format) {
            this.format = format;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
}
