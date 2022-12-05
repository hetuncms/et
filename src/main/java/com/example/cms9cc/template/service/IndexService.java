package com.example.cms9cc.template.service;

import com.example.cms9cc.LiveBean;
import com.example.cms9cc.net.NetInterface;
import com.example.cms9cc.net.NetService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IndexService {

    private final NetInterface netInterface;
    NetService netService;

    public IndexService(NetService netService) {
        this.netService = netService;
        netInterface = netService.getRetrofit().create(NetInterface.class);
    }

    public LiveBean index(String requestBody) {
        try {
            return netInterface.index(requestBody).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LiveBean.LiveItem getLiveItem(Long id) {

        try {
            return netInterface.getLiveItem(id).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIframeLinkByid(String id) {
        try {
            return netInterface.getIframeLinkByid(id).execute().body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
