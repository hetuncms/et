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

    public BasisBean getBasis() {
        BasisBean basisBean = basisMapping.selectOne(new QueryWrapper<>());
        return basisBean;
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

    public HashMap<String, Object> getAllConfig() {
        HashMap<String, Object> configMap = new HashMap<>();

        configMap.put("basis", getBasis());
        configMap.put("statistics", getStatistics());
        configMap.put("bannerAd", getBannerAd());
        configMap.put("coupletsAd", getCoupletsAd());
        configMap.put("floatAd", getFloatAd());
        configMap.put("topAd", getTopAd());

        return configMap;
    }
}