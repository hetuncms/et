package com.example.cms9cc.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.BasisBean;
import com.example.cms9cc.admin.bean.StatisticsBean;
import com.example.cms9cc.admin.mapper.BasisMapping;
import com.example.cms9cc.admin.mapper.StatisticsMapping;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private BasisMapping basisMapping;
    @Resource
    private StatisticsMapping statisticsMapping;

    @GetMapping("/getbasis")
    public BasisBean getBasis() {
        BasisBean basisBean = basisMapping.selectOne(new QueryWrapper<>());
        return basisBean;
    }

    @PostMapping("/editbasis")
    public BasisBean editBasis(@RequestBody BasisBean basisBean) {
        int update = basisMapping.update(basisBean, new UpdateWrapper<>());
        return basisBean;
    }

    @GetMapping("/getstatistics")
    public StatisticsBean getStatistics() {
        StatisticsBean statisticsBean = statisticsMapping.selectOne(new QueryWrapper<>());
        return statisticsBean;
    }

    @PostMapping("/editstatistics")
    public StatisticsBean editStatistics(@RequestBody StatisticsBean basisBean) {
        Long count = statisticsMapping.selectCount(new QueryWrapper<>());
        if (count == 0) {
            statisticsMapping.insert(basisBean);
        } else {
            int update = statisticsMapping.update(basisBean, new UpdateWrapper<>());
        }
        return basisBean;
    }
}