package com.example.cms9cc.admin.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.BasisBean;
import com.example.cms9cc.admin.mapper.BasisMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class BasisController {

    @Resource
    private BasisMapping basisMapping;

    @GetMapping("/getbasis")
    public BasisBean getBasis() {
        BasisBean basisBean = basisMapping.selectOne(new QueryWrapper<>());
        return basisBean;
    }

    @PostMapping("/editbasis")
    public Integer editBasis(@RequestBody BasisBean basisBean) {
        return basisMapping.update(basisBean, new UpdateWrapper<>());
    }

}
