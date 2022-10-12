package com.example.cms9cc.admin.ad.topad;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.FloatBean;
import com.example.cms9cc.admin.mapper.FloatAdMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class FloatAdController {
    @Resource
    private FloatAdMapping floatAdMapping;

    @GetMapping("/getfloatad")
    public FloatBean getFloatAd() {
        FloatBean floatBean = floatAdMapping.selectOne(new QueryWrapper<>());
        return floatBean;
    }

    @PostMapping("/editfloatad")
    public Integer delFloatAd(@RequestBody FloatBean topAdBean) {
        int update = floatAdMapping.update(topAdBean, new UpdateWrapper<>());
        return update;
    }
}
