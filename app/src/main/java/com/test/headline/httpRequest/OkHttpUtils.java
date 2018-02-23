package com.test.headline.httpRequest;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

            clientBuilder.connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            okHttpClient = clientBuilder.build();
        }
        return okHttpClient;
    }

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(@Nullable String message) {
            Log.e(TAG, "loggingInterceptor: " + message);
        }
    });

}
