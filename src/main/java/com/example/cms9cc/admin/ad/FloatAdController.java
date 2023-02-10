package com.example.cms9cc.admin.ad;

import com.example.cms9cc.admin.bean.FloatBean;
import com.example.cms9cc.admin.bean.ResultBean;
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
    public FloatBean getFloatAd() {
        FloatBean floatBean = floatAdMapping.findAll().get(0);
        return floatBean;
    }

    @PostMapping("/editfloatad")
    public ResultBean delFloatAd(@RequestBody FloatBean topAdBean) {
       floatAdMapping.save(topAdBean);
        return new ResultBean.Builder().buildSucces();
    }
}
