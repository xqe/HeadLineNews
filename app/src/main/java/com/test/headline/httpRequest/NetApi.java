package com.test.headline.httpRequest;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xieqe on 2018/2/2.
 */

public interface NetApi {


    @POST
    Observable<Response> uploadCrashInfo(@FieldMap Map<String, String> params);

    @POST
    Observable<Response> uploadCrashFile(@Query("appkey") String appKey, @Query("version ") String version);


}
