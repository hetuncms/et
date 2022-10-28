package com.example.cms9cc.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.*;
import com.example.cms9cc.admin.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Service
public class AdminService {

    private final StatisticsMapping statisticsMapping;
    private final BannerAdMapping bannerAdMapping;
    private final BasisMapping basisMapping;
    private final FloatAdMapping floatAdMapping;
    private final TopAdMapping topAdMapping;
    private final CoupletsAdMapping coupletsAdMapping;
    private final JsAdMapping jsAdMapping;
    @Autowired
    public AdminService(StatisticsMapping statisticsMapping, BannerAdMapping bannerAdMapping, BasisMapping basisMapping, FloatAdMapping floatAdMapping, TopAdMapping topAdMapping, CoupletsAdMapping coupletsAdMapping, JsAdMapping jsAdMapping) {
        this.statisticsMapping = statisticsMapping;
        this.bannerAdMapping = bannerAdMapping;
        this.basisMapping = basisMapping;
        this.floatAdMapping = floatAdMapping;
        this.topAdMapping = topAdMapping;
        this.coupletsAdMapping = coupletsAdMapping;
        this.jsAdMapping = jsAdMapping;
    }

    public BasisBean getBasis() {
        return basisMapping.selectOne(new QueryWrapper<>());
    }

    public JsAdBean getJsAd(){
        return jsAdMapping.selectOne(new QueryWrapper<>());
    }
    public StatisticsBean getStatistics() {
        return statisticsMapping.selectOne(new QueryWrapper<>());
    }

    public List<BannerAdBean> getBannerAd() {

        return bannerAdMapping.selectList(getQueryWrapper());
    }

    public CoupletsMapBean getCoupletsAd() {
        List<CoupletsBean> topAdBeans = coupletsAdMapping.selectList(getQueryWrapper());
        CoupletsMapBean coupletsMapBean = new CoupletsMapBean();
        topAdBeans.forEach(coupletsBean -> {
            switch (coupletsBean.getLocation()) {
                case "对联左" -> coupletsMapBean.setLeftAd(coupletsBean);
                case "对联右" -> coupletsMapBean.setRightAd(coupletsBean);
            }
        });
        return coupletsMapBean;
    }

    public FloatBean getFloatAd() {
        return floatAdMapping.selectOne(getQueryWrapper());
    }

    public List<TopAdBean> getTopAd() {
        return topAdMapping.selectList(getQueryWrapper());
    }

    private <T> QueryWrapper<T> getQueryWrapper(){

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return new QueryWrapper<T>().gt("status", today);
    }

    public AllAdBean getAllConfig() {
        return new AllAdBean(getBannerAd(),getBasis(),getCoupletsAd(),getFloatAd(),getJsAd(),getStatistics(),getTopAd());
    }
}