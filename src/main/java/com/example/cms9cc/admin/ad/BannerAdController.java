package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.BannerAdBean;
import com.example.cms9cc.admin.bean.ResultBean;
import com.example.cms9cc.admin.repositories.BannerAdMapping;
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
        List<BannerAdBean> topAdBeans = bannerAdMapping.findAll();
        return topAdBeans;
    }

    @PostMapping("/addbannerad")
    public BannerAdBean addBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        return bannerAdMapping.save(bannerAdBean);
    }

    @PostMapping("/editbannerad")
    public ResultBean editBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        BannerAdBean save = bannerAdMapping.save(bannerAdBean);
        return new ResultBean.Builder().buildSucces();
    }

    @PostMapping("/delbannerad")
    public void delBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        bannerAdMapping.deleteById(bannerAdBean.getId());
    }
}
