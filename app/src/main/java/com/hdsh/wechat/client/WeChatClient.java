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
    Retrofit mRetrofit;
    RobotApi mRobotApi;

    private WeChatClient() {
        //美女图
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //图灵机器人
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.tuling123.com/")
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

    public RobotApi getRobotApi() {
        if (mRobotApi == null) {
            mRobotApi = mRetrofit.create(RobotApi.class);
        }
        return mRobotApi;
    }

}
