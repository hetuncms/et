package com.example.cms9cc.net;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.example.cms9cc.Config;
import com.example.cms9cc.LiveBean;
import com.example.cms9cc.tools.fastjson_converter.GsonConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.Map;

@Service
public class NetService {

    Config config;
    @Autowired
    public NetService(Config config) {
        this.config = config;
        retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(config.getBaseUrl())
                .build();
    }

    Retrofit retrofit;
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
