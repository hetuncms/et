package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.ad.service.TopAdService;
import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.bean.TopAdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class TopAdController {

    private final TopAdService topAdService;

    @Autowired
    public TopAdController(TopAdService topAdService) {
        this.topAdService = topAdService;
    }

    @GetMapping("/gettopad")
    public BaseAdminBean<List<TopAdBean>> getTopAd() {
        List<TopAdBean> topAdBeans = topAdService.getTopAdList();
        return new BaseAdminBean.Builder<List<TopAdBean>>().buildSucces(topAdBeans);
    }

    @PostMapping("/addtopad")
    public BaseAdminBean<TopAdBean> addTopAd(@RequestBody TopAdBean topAdBean) {
        TopAdBean topAdBean1 = topAdService.addTopAd(topAdBean);
        return new BaseAdminBean.Builder<TopAdBean>().buildSucces(topAdBean1);
    }

    @PostMapping("/deltopad")
    public BaseAdminBean<TopAdBean> delTopAd(@RequestBody TopAdBean topAdBean) {
        topAdService.deleteTopAd(topAdBean);
        return new BaseAdminBean.Builder<TopAdBean>().buildSucces();
    }

    @PostMapping("/edittopad")
    public BaseAdminBean<TopAdBean> editTopAd(@RequestBody TopAdBean topAdBean) {
        return new BaseAdminBean.Builder<TopAdBean>().buildSucces(topAdService.updateTopAd(topAdBean));
    }
}
