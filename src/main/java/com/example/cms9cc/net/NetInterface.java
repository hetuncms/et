package com.example.cms9cc.net;

import com.example.cms9cc.LiveBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;
import java.util.concurrent.Executor;

public interface NetInterface {
    @POST("index")
    Call<LiveBean> index(@Body String requestBody);
    @FormUrlEncoded
    @POST("get_iframe_link_byid")
    Call<ResponseBody> getIframeLinkByid(@Field("id") String id);

    @GET("getLiveItem")
    Call<LiveBean.LiveItem> getLiveItem(@Query("liveId") Long liveId);
}
