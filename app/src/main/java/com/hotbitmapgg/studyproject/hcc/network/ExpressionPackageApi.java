package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.ExpressionPackage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ExpressionPackageApi
{

    @GET("search")
    Observable<List<ExpressionPackage>> search(@Query("q") String query);
}
