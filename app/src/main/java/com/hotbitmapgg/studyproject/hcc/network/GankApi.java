package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.Gank;
import com.hotbitmapgg.studyproject.hcc.model.GankDayInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface GankApi
{

    @GET("data/{type}/{number}/{page}")
    Observable<Gank> getGankDatas(@Path("type") String type, @Path("number") int number, @Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankDayInfo> getGankDayData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
    Observable<Gank> searchGank(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
