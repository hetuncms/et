package com.example.cms9cc.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.*;
import com.example.cms9cc.admin.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

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
        List<BannerAdBean> topAdBeans = bannerAdMapping.selectList(getQueryWrapper());

        return topAdBeans;
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
        FloatBean floatBean = floatAdMapping.selectOne(getQueryWrapper());
        return floatBean;
    }

    public List<TopAdBean> getTopAd() {
        List<TopAdBean> topAdBeans = topAdMapping.selectList(getQueryWrapper());
        return topAdBeans;
    }

    private <T> QueryWrapper<T> getQueryWrapper(){

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        QueryWrapper<T> status =  new QueryWrapper<T>().gt("status", today);
        return status;
    }

    public AllAdBean getAllConfig() {
        return new AllAdBean(getBannerAd(),getBasis(),getCoupletsAd(),getFloatAd(),getJsAdBean(),getStatistics(),getTopAd());
    }
}