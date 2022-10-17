package com.example.cms9cc.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.*;
import com.example.cms9cc.admin.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {

    @Resource
    private StatisticsMapping statisticsMapping;
    @Resource
    private BannerAdMapping bannerAdMapping;
    @Resource
    private BasisMapping basisMapping;
    @Resource
    private FloatAdMapping floatAdMapping;
    @Resource
    private TopAdMapping topAdMapping;
    @Resource
    private CoupletsAdMapping coupletsAdMapping;
    @Resource
    private JsAdMapping jsAdMapping;

    public BasisBean getBasis() {
        BasisBean basisBean = basisMapping.selectOne(new QueryWrapper<>());
        return basisBean;
    }

    public JsAdBean getJsAdBean(){
        return jsAdMapping.selectOne(new QueryWrapper<>());
    }
    public StatisticsBean getStatistics() {
        StatisticsBean statisticsBean = statisticsMapping.selectOne(new QueryWrapper<>());
        return statisticsBean;
    }

    public List<BannerAdBean> getBannerAd() {
        List<BannerAdBean> topAdBeans = bannerAdMapping.selectList(new QueryWrapper<>());
        return topAdBeans;
    }

    public List<CoupletsBean> getCoupletsAd() {
        List<CoupletsBean> topAdBeans = coupletsAdMapping.selectList(new QueryWrapper<>());
        return topAdBeans;
    }

    public FloatBean getFloatAd() {
        FloatBean floatBean = floatAdMapping.selectOne(new QueryWrapper<>());
        return floatBean;
    }

    public List<TopAdBean> getTopAd() {
        List<TopAdBean> topAdBeans = topAdMapping.selectList(new QueryWrapper<>());
        return topAdBeans;
    }

    public AllAdBean getAllConfig() {
        return new AllAdBean(getBannerAd(),getBasis(),getCoupletsAd(),getFloatAd(),getJsAdBean(),getStatistics(),getTopAd());
    }
}