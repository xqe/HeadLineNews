package com.test.headline.httpRequest;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xieqe on 2018/2/2.
 */

public class RetrofitManager {

    //新闻头条base url
    private static final String BASE_URL = "http://v.juhe.cn/toutiao/index";
    private Retrofit retrofit;

    private RetrofitManager() {
        init();
    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    private void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpUtils.getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NetApi getService() {
        return retrofit.create(NetApi.class);
    }
}
