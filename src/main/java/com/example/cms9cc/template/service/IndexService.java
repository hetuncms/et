package com.example.cms9cc.template.service;

import com.example.cms9cc.BaseListBean;
import com.example.cms9cc.LiveBean;
import com.example.cms9cc.net.NetInterface;
import com.example.cms9cc.net.NetService;
import com.example.cms9cc.template.bean.BaseListDateBean;
import com.example.cms9cc.template.bean.PlayInfoBean;
import com.example.cms9cc.template.bean.PredictionsIndexBean;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class IndexService {

    private final NetInterface netInterface;
    NetService netService;

    public IndexService(NetService netService) {
        this.netService = netService;
        netInterface = netService.getRetrofit().create(NetInterface.class);
    }

    public BaseListDateBean<List<LiveBean.LiveItem>> getDataByDate(int liveType, int page, String date,int limit) {
        try {
            BaseListDateBean<List<LiveBean.LiveItem>> body = netInterface.getDataAndDate(liveType, page,limit,date).execute().body();
            return body;
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }

    public LiveBean.LiveItem getLiveItem(Long id) {
        try {
            return netInterface.getLiveItem(id).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public BaseListBean<PredictionsIndexBean> getPredict(String date) {
        Call<BaseListBean<PredictionsIndexBean>> predict = netInterface.getPredict(0,date);
        try {
            return predict.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
