package com.example.cms9cc.admin.basis;

import com.example.cms9cc.admin.bean.BaseAdminBean;
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
    public BaseAdminBean<StatisticsBean> getStatistics() {
        StatisticsBean statisticsBean = statisticsMapping.findAll().get(0);
        return new BaseAdminBean.Builder<StatisticsBean>().buildSucces(statisticsBean);
    }

    @PostMapping("/editstatistics")
    public BaseAdminBean<StatisticsBean> editStatistics(@RequestBody StatisticsBean statisticsBean) {
        if (statisticsBean != null && !StringUtils.isEmpty(statisticsBean.getJsUrl())) {
            statisticsBean.setId(1);
            StatisticsBean save = statisticsMapping.save(statisticsBean);
            System.out.println("save = " + save);
            return new BaseAdminBean.Builder<StatisticsBean>().buildSucces(save);
        }
        return new BaseAdminBean.Builder<StatisticsBean>().buildSucces();
    }
}
