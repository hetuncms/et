/**
 * Copyright 2022 bejson.com
 */
package com.example.cms9cc.template.bean;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

/**
 * Auto-generated: 2022-10-17 3:32:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PaiHangBean {

    @JsonAlias("arr")
    private List<Item> items;
    private int catid;
    private int comid;
    private int type;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public class Item {

        private String id;
        private String competitionId;
        private String type;
        private String team;
        private String name;
        private String times;
        private String win;
        private String ping;
        private String loss;
        private String goal;
        private String loss_goal;
        private String win_goal;
        private String point;
        private String duration;
        private String group;
        private String rank;
        private String goup_name;
        private String gol_dian;
        private String is_lan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(String competitionId) {
            this.competitionId = competitionId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public String getPing() {
            return ping;
        }

        public void setPing(String ping) {
            this.ping = ping;
        }

        public String getLoss() {
            return loss;
        }

        public void setLoss(String loss) {
            this.loss = loss;
        }

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public String getLoss_goal() {
            return loss_goal;
        }

        public void setLoss_goal(String loss_goal) {
            this.loss_goal = loss_goal;
        }

        public String getWin_goal() {
            return win_goal;
        }

        public void setWin_goal(String win_goal) {
            this.win_goal = win_goal;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getGoup_name() {
            return goup_name;
        }

        public void setGoup_name(String goup_name) {
            this.goup_name = goup_name;
        }

        public String getGol_dian() {
            return gol_dian;
        }

        public void setGol_dian(String gol_dian) {
            this.gol_dian = gol_dian;
        }

        public String getIs_lan() {
            return is_lan;
        }

        public void setIs_lan(String is_lan) {
            this.is_lan = is_lan;
        }

    }
}
