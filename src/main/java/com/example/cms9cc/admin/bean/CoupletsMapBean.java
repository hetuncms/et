package com.example.cms9cc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

public class CoupletsMapBean {
    private CoupletsBean leftAd;
    private CoupletsBean rightAd;

    public CoupletsMapBean(CoupletsBean leftAd, CoupletsBean rightAd) {
        this.leftAd = leftAd;
        this.rightAd = rightAd;
    }

    public CoupletsMapBean() {
    }

    public CoupletsBean getLeftAd() {
        return leftAd;
    }

    public void setLeftAd(CoupletsBean leftAd) {
        this.leftAd = leftAd;
    }

    public CoupletsBean getRightAd() {
        return rightAd;
    }

    public void setRightAd(CoupletsBean rightAd) {
        this.rightAd = rightAd;
    }
}
