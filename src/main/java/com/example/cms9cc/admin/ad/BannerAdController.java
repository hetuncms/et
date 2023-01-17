package com.example.cms9cc.admin.ad;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.BannerAdBean;
import com.example.cms9cc.admin.mapper.BannerAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class BannerAdController {

    private final BannerAdMapping bannerAdMapping;

    @Autowired
    public BannerAdController(BannerAdMapping bannerAdMapping) {
        this.bannerAdMapping = bannerAdMapping;
    }

    @GetMapping("/getbannerad")
    public List<BannerAdBean> getBannerAd() {
        List<BannerAdBean> topAdBeans = bannerAdMapping.selectList(new QueryWrapper<>());
        return topAdBeans;
    }

    @PostMapping("/addbannerad")
    public Integer addBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        int insert = bannerAdMapping.insert(bannerAdBean);
        return insert;
    }

    @PostMapping("/delbannerad")
    public Integer delBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        int i = bannerAdMapping.deleteById(bannerAdBean.getId());
        return i;
    }
}
