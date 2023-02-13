package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.bean.CoupletsBean;
import com.example.cms9cc.admin.repositories.CoupletsAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class CoupletsAdController {

    private final CoupletsAdMapping coupletsAdMapping;

    @Autowired
    public CoupletsAdController(CoupletsAdMapping coupletsAdMapping) {
        this.coupletsAdMapping = coupletsAdMapping;
    }

    @GetMapping("/getcoupletsad")
    public BaseAdminBean<List<CoupletsBean>> getCoupletsAd() {
        List<CoupletsBean> topAdBeans = coupletsAdMapping.findAll();

        return new BaseAdminBean.Builder<List<CoupletsBean>>().buildSucces(topAdBeans);
    }

    @PostMapping("/editcoupletsad")
    public BaseAdminBean<CoupletsBean> editCoupletsad(@RequestBody CoupletsBean coupletsBean) {
        CoupletsBean save = coupletsAdMapping.save(coupletsBean);
        return new BaseAdminBean.Builder<CoupletsBean>().buildSucces(save);
    }
}
