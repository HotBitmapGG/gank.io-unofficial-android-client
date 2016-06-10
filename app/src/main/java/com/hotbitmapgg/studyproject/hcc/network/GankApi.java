package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface GankApi
{
    @GET("data/{type}/{number}/{page}")
    Observable<Gank> getGankDatas(@Path("type") String type, @Path("number") int number, @Path("page") int page);
}
