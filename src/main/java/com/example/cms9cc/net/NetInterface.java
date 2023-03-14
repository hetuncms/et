package com.example.cms9cc.net;

import com.example.cms9cc.BaseBean;
import com.example.cms9cc.BaseListBean;
import com.example.cms9cc.LiveBean;
import com.example.cms9cc.template.bean.BaseListDateBean;
import com.example.cms9cc.template.bean.PlayInfoBean;
import com.example.cms9cc.template.bean.PredictionsBean;
import com.example.cms9cc.template.bean.PredictionsIndexBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

public interface NetInterface {
    @GET("index")
    Call<BaseListBean<List<LiveBean.LiveItem>>> getData(@Query("liveType") int liveType, @Query("page") int page,@Query("limit") int limit);
    @GET("get_data_and_date")
    Call<BaseListDateBean<List<LiveBean.LiveItem>>> getDataAndDate(@Query("liveType") int liveType, @Query("page") int page, @Query("limit") int limit,
                                                                   @Query("date") String  date);
    @GET("get_predictions")
    Call<BaseListBean<PredictionsIndexBean>> getPredict(@Query("page") int page, @Query("date") String date);
    @GET("getLiveItem")
    Call<LiveBean.LiveItem> getLiveItem(@Query("liveId") Long liveId);

    @GET("getPlayInfo")
    Call<PlayInfoBean> getPlayInfo(@Query("matchId") Long id);
}
