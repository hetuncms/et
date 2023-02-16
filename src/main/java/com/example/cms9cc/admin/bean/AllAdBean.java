package com.example.cms9cc.admin.bean;

import lombok.Data;

import java.util.List;
@Data
public class AllAdBean {
    private List<BannerAdBean> bannerAd;
    private BasisBean basis;
    private CoupletsMapBean couplets;
    private FloatBean floatAd;
    private JsAdBean jsAd;
    private StatisticsBean statistics;
    private List<TopAdBean> topAd;

    public AllAdBean(List<BannerAdBean> bannerAd, BasisBean basis, CoupletsMapBean couplets, FloatBean floatAd, JsAdBean jsAd, StatisticsBean statistics, List<TopAdBean> topAd) {
        this.bannerAd = bannerAd;
        this.basis = basis;
        this.couplets = couplets;
        this.floatAd = floatAd;
        this.jsAd = jsAd;
        this.statistics = statistics;
        this.topAd = topAd;
    }
}
