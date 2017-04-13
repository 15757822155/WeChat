package com.hdsh.wechat.client;

import com.hdsh.wechat.entity.RobotInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017-04-11 0011.
 */

public interface RobotApi {
    @GET("openapi/api")
    Observable<RobotInfo> getResult(@Query("key")String key, @Query("info") String info);
}
