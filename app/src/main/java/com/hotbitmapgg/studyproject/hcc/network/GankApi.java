package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.Gank;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface GankApi
{

    @GET("data/福利/{number}/{page}")
    Observable<GankResult> getBeauties(@Path("number") int number, @Path("page") int page);

    @GET("data/{type}/{number}/{page}")
    Observable<Gank> getGankDatas(@Path("type") String type, @Path("number") int number, @Path("page") int page);
}
