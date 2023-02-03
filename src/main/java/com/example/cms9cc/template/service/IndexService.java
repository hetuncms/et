package com.example.cms9cc.template.service;

import com.example.cms9cc.LiveBean;
import com.example.cms9cc.net.NetInterface;
import com.example.cms9cc.net.NetService;
import com.example.cms9cc.template.bean.PlayInfoBean;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

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
            LiveBean body = netInterface.index(requestBody).execute().body();
            return body;
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

    public PlayInfoBean getLiveInfo(Long id) {
        Call<PlayInfoBean> playInfo = netInterface.getPlayInfo(id);
        Response<PlayInfoBean> execute = null;
        try {
            execute = playInfo.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PlayInfoBean body = execute.body();
        return body;
    }
}
