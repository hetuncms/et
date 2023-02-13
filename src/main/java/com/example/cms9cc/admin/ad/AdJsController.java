package com.example.cms9cc.admin.ad;


import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.bean.JsAdBean;
import com.example.cms9cc.admin.repositories.JsAdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdJsController {
    private final JsAdRepo jsAdMapping;

    @Autowired
    public AdJsController(JsAdRepo jsAdMapping) {
        this.jsAdMapping = jsAdMapping;
    }

    @PostMapping("/changejsad")
    public BaseAdminBean<JsAdBean> changeAdJs(@RequestBody JsAdBean jsAdBean) {
        JsAdBean save = jsAdMapping.save(jsAdBean);

        return new BaseAdminBean.Builder<JsAdBean>().buildSucces(save);
    }

    @GetMapping("/getjsad")
    public BaseAdminBean<JsAdBean> getJsAd() {
        JsAdBean jsAdBean = jsAdMapping.findAll().get(0);
        return new BaseAdminBean.Builder<JsAdBean>().buildSucces(jsAdBean);
    }

}
