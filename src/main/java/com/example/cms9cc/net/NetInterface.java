package com.example.cms9cc.net;

import com.example.cms9cc.LiveBean;
import com.example.cms9cc.template.bean.PlayInfoBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;
import java.util.concurrent.Executor;

public interface NetInterface {
    @GET("index")
    Call<LiveBean> index(@Query("liveType") int liveType,@Query("page") int page);
    @GET("getLiveItem")
    Call<LiveBean.LiveItem> getLiveItem(@Query("liveId") Long liveId);

    @GET("getPlayInfo")
    Call<PlayInfoBean> getPlayInfo(@Query("matchId") Long id);
}
