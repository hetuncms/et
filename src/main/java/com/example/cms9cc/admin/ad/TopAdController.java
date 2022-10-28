package com.example.cms9cc.admin.ad.topad;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cms9cc.admin.bean.TopAdBean;
import com.example.cms9cc.admin.mapper.TopAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController

@RequestMapping("/admin")
public class TopAdController {

    private final TopAdMapping topAdMapping;
    @Autowired
    public TopAdController(TopAdMapping topAdMapping) {
        this.topAdMapping = topAdMapping;
    }

    @GetMapping("/gettopad")
    public List<TopAdBean> getTopAd() {
        List<TopAdBean> topAdBeans = topAdMapping.selectList(new QueryWrapper<>());
        return topAdBeans;
    }

    @PostMapping("/addtopad")
    public Integer addTopAd(@RequestBody TopAdBean topAdBean) {
        int insert = topAdMapping.insert(topAdBean);
        return insert;
    }

    @PostMapping("/deltopad")
    public Integer delTopAd(@RequestBody TopAdBean topAdBean) {
        int i = topAdMapping.deleteById(topAdBean.getId());
        return i;
    }
}
