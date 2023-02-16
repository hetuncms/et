package com.example.cms9cc.admin.ad.service;

import com.example.cms9cc.admin.bean.TopAdBean;
import com.example.cms9cc.admin.repositories.TopAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopAdService {


    private final TopAdRepository topAdRepository;

    @Autowired
    public TopAdService(TopAdRepository topAdRepository) {
        this.topAdRepository = topAdRepository;
    }

    public List<TopAdBean> getTopAdList() {
        Sort sort = Sort.by(Sort.Direction.ASC, "sort");
        return topAdRepository.findAllByStatusTimeAfterOrderBySort(new Date(System.currentTimeMillis()));
    }

    public TopAdBean addTopAd(TopAdBean topAdBean) {
        return topAdRepository.save(topAdBean);
    }

    public void deleteTopAd(TopAdBean topAdBean) {
        topAdRepository.deleteById(topAdBean.getId());
    }

    public TopAdBean updateTopAd(TopAdBean topAdBean) {
        return topAdRepository.save(topAdBean);
    }
}
