package com.example.cms9cc.admin.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.BasisBean;
import com.example.cms9cc.admin.mapper.BasisMapping;
import com.example.cms9cc.tools.RestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/admin")
public class BasisController {

    private final RestartService restartService;

    private final BasisMapping basisMapping;
    @Value("${template.path}")
    private String templatePath;

    @Autowired
    public BasisController(RestartService restartService, BasisMapping basisMapping) {
        this.restartService = restartService;
        this.basisMapping = basisMapping;
    }

    @GetMapping("/getbasis")
    public BasisBean getBasis() {
        BasisBean basisBean = basisMapping.selectOne(new QueryWrapper<>());
        basisBean.setTemplates(getTemplates());
        return basisBean;
    }

    @PostMapping("/editbasis")
    public Integer editBasis(@RequestBody BasisBean basisBean) {
        int update = basisMapping.update(basisBean, new UpdateWrapper<>());
        restartService.restartApp();
        return update;
    }

    private String[] getTemplates() {
        File file = null;
        try {
            file = new File(new URI(templatePath).getSchemeSpecificPart());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return file.list();
    }
}
