package com.test.headline.httpRequest;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xieqe on 2018/2/2.
 */

public interface NetApi {


    @GET("/index")
    Observable<Response> getNews(@Query("type")String type,@Query("key")String appKey);

}
