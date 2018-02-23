package com.test.headline.httpRequest;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xieqe on 2018/2/2.
 */

public class RetrofitManager {
    private Retrofit retrofit;
    private String baseUrl;
    private OkHttpClient client;

    private RetrofitManager() {
        client = OkHttpUtils.getOkHttpClient();
    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**设置base url*/
    void setBaseUrl(String url) {
        baseUrl = url;
    }

    /**设置请求头*/
    void setHeaders(final Map<String,String> headers) {
        client = client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();
                Request.Builder builder = oldRequest.newBuilder();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.header(entry.getKey(),entry.getValue());
                }
                return chain.proceed(builder.build());
            }
        }).build();
    }

    /**获取retrofit实例*/
    Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
