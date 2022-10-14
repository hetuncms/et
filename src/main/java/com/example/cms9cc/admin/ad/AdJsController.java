package com.example.cms9cc.admin.ad;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.JsAdBean;
import com.example.cms9cc.admin.mapper.JsAdMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdJsController {

    @Resource
    JsAdMapping jsAdMapping;


    @PostMapping("/changejsad")
    public Integer changeAdJs(@RequestBody JsAdBean jsAdBean) {
        return jsAdMapping.update(jsAdBean, new UpdateWrapper<>());
    }

    @GetMapping("/getjsad")
    public JsAdBean getJsAd() {
        return jsAdMapping.selectOne(new QueryWrapper<>());
    }


}
