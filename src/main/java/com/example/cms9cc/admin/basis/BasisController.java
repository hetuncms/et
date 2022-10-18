package com.example.cms9cc.admin.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.BasisBean;
import com.example.cms9cc.admin.mapper.BasisMapping;
import com.example.cms9cc.tools.RestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class BasisController {
    @Resource
    private RestartService restartService;

    @Resource
    private BasisMapping basisMapping;

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
    @Value("${template.path}")
    private String templatePath;
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
