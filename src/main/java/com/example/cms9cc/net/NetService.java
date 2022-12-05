package com.example.cms9cc.net;

import com.example.cms9cc.Config;
import com.example.cms9cc.tools.fastjson_converter.FastJsonConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Service
public class NetService {

    Config config;
    @Autowired
    public NetService(Config config) {
        this.config = config;
        retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(config.getBaseUrl())
                .build();
    }

    Retrofit retrofit;
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
