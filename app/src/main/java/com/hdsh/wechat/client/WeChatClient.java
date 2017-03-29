package com.hdsh.wechat.client;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-03-24 0024.
 */

public class WeChatClient {
    Retrofit retrofit;
    FaceImageApi faceImageApi;
    private static WeChatClient weChatClient;

    private WeChatClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static WeChatClient getInstance() {
        if (weChatClient == null) {
            weChatClient = new WeChatClient();
        }
        return weChatClient;
    }

    public FaceImageApi getFaceImageApi() {
        if (faceImageApi == null) {
            faceImageApi = retrofit.create(FaceImageApi.class);
        }
        return faceImageApi;
    }

}
