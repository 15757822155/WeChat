package com.hdsh.wechat.client;

import com.hdsh.wechat.entity.FaceImageInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017-03-24 0024.
 */

public interface FaceImageApi {
    @GET("api/data/福利/{count}/1")
    Observable<FaceImageInfo> getFaceImage(@Path("count") String count);
}
