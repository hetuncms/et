package com.example.cms9cc.net;

import com.example.cms9cc.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Service
public class NetService {

    Config config;
    Retrofit retrofit;

    @Autowired
    public NetService(Config config) {
        this.config = config;
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create());

//            java.net.Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress("localhost", 10809));
//        OkHttpClient.Builder proxy1 = new OkHttpClient.Builder().proxy(proxy);
//        builder.client(proxy1.build());
        retrofit = builder
                .baseUrl(config.getBaseUrl())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
