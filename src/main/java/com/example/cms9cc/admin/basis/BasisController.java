package com.example.cms9cc.admin.basis;

import com.example.cms9cc.admin.bean.BasisBean;
import com.example.cms9cc.admin.repositories.BasisMapping;
import com.example.cms9cc.tools.RestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/admin")
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
        BasisBean basisBean = basisMapping.findAll().get(0);
        basisBean.setTemplates(Arrays.asList(getTemplates()));
        return basisBean;
    }

    @PostMapping("/editbasis")
    public void editBasis(@RequestBody BasisBean basisBean) {
        basisMapping.save(basisBean);
        restartService.restartApp();
    }

    private String[] getTemplates() {
        File file = null;
        try {
            file = ResourceUtils.getFile(templatePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file.list();
    }
}
