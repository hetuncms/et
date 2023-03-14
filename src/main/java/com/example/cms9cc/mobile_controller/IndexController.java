package com.example.cms9cc.mobile_controller;

import com.example.cms9cc.LiveBean;
import com.example.cms9cc.template.bean.BaseListDateBean;
import com.example.cms9cc.template.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/getData")
    public BaseListDateBean<List<LiveBean.LiveItem>> getDataByDate(@RequestParam("live_type") int liveType, @RequestParam("page") int page,
                                                                   @RequestParam("date") String date,@RequestParam("limit") int limit) {
        return indexService.getDataByDate(liveType, page, date,limit);
    }
}
