package com.example.cms9cc.admin.basis;

import com.example.cms9cc.admin.bean.StatisticsBean;
import com.example.cms9cc.admin.repositories.StatisticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class StatisticsController {

    StatisticsRepo statisticsMapping;

    @Autowired
    public StatisticsController(StatisticsRepo statisticsMapping) {
        this.statisticsMapping = statisticsMapping;
    }

    @GetMapping("/getstatistics")
    public StatisticsBean getStatistics() {
        return statisticsMapping.findAll().get(0);
    }

    @PostMapping("/editstatistics")
    public Integer editStatistics(@RequestBody StatisticsBean statisticsBean) {

        if (statisticsBean != null && !StringUtils.isEmpty(statisticsBean.getJsUrl())) {
            statisticsMapping.save(statisticsBean);
        }
        return null;
    }
}
