package com.test.headline.httpRequest;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xieqe on 2018/2/2.
 */

public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    private static OkHttpClient okHttpClient;
    private static boolean isDebug = false;

    /**获取自定义属性的OkHttpClient*/
    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if (isDebug) {
                clientBuilder.addInterceptor(loggingInterceptor);
            }
            clientBuilder.addInterceptor(publicInterceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            okHttpClient = clientBuilder.build();
        }
        return okHttpClient;
    }

    private static Interceptor publicInterceptor =  new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            //添加公共参数
            Request request = oldRequest.newBuilder()
                    //.header()
                    .build();
            return chain.proceed(request);
        }
    };

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(@Nullable String message) {
            Log.e(TAG, "loggingInterceptor: " + message);
        }
    });

}
