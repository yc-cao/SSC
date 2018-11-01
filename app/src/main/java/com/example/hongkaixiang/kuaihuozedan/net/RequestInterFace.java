package com.example.hongkaixiang.kuaihuozedan.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterFace {
    @GET("/")
    Call<WebUrlBean> getCall();
}
