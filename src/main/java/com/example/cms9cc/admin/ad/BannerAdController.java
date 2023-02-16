package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.BannerAdBean;
import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.repositories.BannerAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public BaseAdminBean<List<BannerAdBean>> getBannerAd() {
        List<BannerAdBean> topAdBeans = bannerAdMapping.findAllByStatusTimeAfterOrderBySort(new Date(System.currentTimeMillis()));
        return new BaseAdminBean.Builder<List<BannerAdBean>>().buildSucces(topAdBeans);
    }

    @PostMapping("/addbannerad")
    public BaseAdminBean<BannerAdBean> addBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        BannerAdBean save = bannerAdMapping.save(bannerAdBean);
        return new BaseAdminBean.Builder<BannerAdBean>().buildSucces(save);
    }

    @PostMapping("/editbannerad")
    public BaseAdminBean<BannerAdBean> editBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        BannerAdBean save = bannerAdMapping.save(bannerAdBean);
        return new BaseAdminBean.Builder<BannerAdBean>().buildSucces(save);
    }

    @PostMapping("/delbannerad")
    public BaseAdminBean<BannerAdBean> delBannerAd(@RequestBody BannerAdBean bannerAdBean) {
        bannerAdMapping.deleteById(bannerAdBean.getId());
        return new BaseAdminBean.Builder<BannerAdBean>().buildSucces();
    }
}
