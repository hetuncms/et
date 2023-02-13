package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.bean.FloatBean;
import com.example.cms9cc.admin.repositories.FloatAdMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class FloatAdController {

    private final FloatAdMapping floatAdMapping;

    @Autowired
    public FloatAdController(FloatAdMapping floatAdMapping) {
        this.floatAdMapping = floatAdMapping;
    }

    @GetMapping("/getfloatad")
    public BaseAdminBean<FloatBean> getFloatAd() {
        FloatBean floatBean = floatAdMapping.findAll().get(0);
        return new BaseAdminBean.Builder<FloatBean>().buildSucces(floatBean);
    }

    @PostMapping("/editfloatad")
    public BaseAdminBean<FloatBean> delFloatAd(@RequestBody FloatBean topAdBean) {
        FloatBean save = floatAdMapping.save(topAdBean);
        return new BaseAdminBean.Builder<FloatBean>().buildSucces(save);
    }
}
