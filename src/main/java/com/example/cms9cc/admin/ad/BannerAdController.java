package com.example.cms9cc.admin.ad.topad;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.BannerAdBean;
import com.example.cms9cc.admin.mapper.BannerAdMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController

@RequestMapping("/admin")
public class BannerAdController {
    @Resource
    private BannerAdMapping bannerAdMapping;

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
