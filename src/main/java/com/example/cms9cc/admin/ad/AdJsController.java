package com.example.cms9cc.admin.ad;


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
    public JsAdBean changeAdJs(@RequestBody JsAdBean jsAdBean) {

        return jsAdMapping.save(jsAdBean);
    }

    @GetMapping("/getjsad")
    public JsAdBean getJsAd() {
        return jsAdMapping.findAll().get(0);
    }

}
