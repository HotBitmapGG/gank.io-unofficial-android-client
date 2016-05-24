package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.GankAndroid;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface GankApi
{

    @GET("data/福利/{number}/{page}")
    Observable<GankResult> getBeauties(@Path("number") int number, @Path("page") int page);

    @GET("data/Android/{number}/{page}")
    Observable<GankAndroid> getAndroidDatas(@Path("number") int number, @Path("page") int page);
}
