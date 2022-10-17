package com.example.cms9cc.admin.bean;

import java.util.List;

public class AllAdBean {
    private List<BannerAdBean> bannerAd;
    private BasisBean basis;
    private List<CoupletsBean> couplets;
    private FloatBean floatAd;
    private JsAdBean jsAd;
    private StatisticsBean statistics;
    private List<TopAdBean> topAd;

    public AllAdBean(List<BannerAdBean> bannerAd, BasisBean basis, List<CoupletsBean> couplets, FloatBean floatAd, JsAdBean jsAd, StatisticsBean statistics, List<TopAdBean> topAd) {
        this.bannerAd = bannerAd;
        this.basis = basis;
        this.couplets = couplets;
        this.floatAd = floatAd;
        this.jsAd = jsAd;
        this.statistics = statistics;
        this.topAd = topAd;
    }

    public List<BannerAdBean> getBannerAd() {
        return bannerAd;
    }

    public void setBannerAd(List<BannerAdBean> bannerAd) {
        this.bannerAd = bannerAd;
    }

    public BasisBean getBasis() {
        return basis;
    }

    public void setBasis(BasisBean basis) {
        this.basis = basis;
    }

    public List<CoupletsBean> getCouplets() {
        return couplets;
    }

    public void setCouplets(List<CoupletsBean> couplets) {
        this.couplets = couplets;
    }

    public FloatBean getFloatAd() {
        return floatAd;
    }

    public void setFloatAd(FloatBean floatAd) {
        this.floatAd = floatAd;
    }

    public JsAdBean getJsAd() {
        return jsAd;
    }

    public void setJsAd(JsAdBean jsAd) {
        this.jsAd = jsAd;
    }

    public StatisticsBean getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsBean statistics) {
        this.statistics = statistics;
    }

    public List<TopAdBean> getTopAd() {
        return topAd;
    }

    public void setTopAd(List<TopAdBean> topAd) {
        this.topAd = topAd;
    }
}
