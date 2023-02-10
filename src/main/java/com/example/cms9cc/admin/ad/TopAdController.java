package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.ResultBean;
import com.example.cms9cc.admin.bean.TopAdBean;
import com.example.cms9cc.admin.repositories.TopAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class TopAdController {

    private final TopAdMapping topAdMapping;

    @Autowired
    public TopAdController(TopAdMapping topAdMapping) {
        this.topAdMapping = topAdMapping;
    }

    @GetMapping("/gettopad")
    public List<TopAdBean> getTopAd() {
        List<TopAdBean> topAdBeans = topAdMapping.findAll();
        return topAdBeans;
    }

    @PostMapping("/addtopad")
    public ResultBean addTopAd(@RequestBody TopAdBean topAdBean) {
        topAdMapping.save(topAdBean);
        return new ResultBean.Builder().buildSucces();
    }

    @PostMapping("/deltopad")
    public ResultBean delTopAd(@RequestBody TopAdBean topAdBean) {
     topAdMapping.deleteById(topAdBean.getId());
        return new ResultBean.Builder().buildSucces();
    }

    @PostMapping("/edittopad")
    public ResultBean editTopAd(@RequestBody TopAdBean topAdBean){
        topAdMapping.save(topAdBean);
        return new ResultBean.Builder().buildSucces();
    }
}
