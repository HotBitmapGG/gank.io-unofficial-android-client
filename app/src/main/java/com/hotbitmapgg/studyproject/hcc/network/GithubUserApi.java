package com.hotbitmapgg.studyproject.hcc.network;


import com.hotbitmapgg.studyproject.hcc.model.GithubUser;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubUserApi
{

    //https://api.github.com/search/users?q=HotBitmapGG
    @GET("search/users")
    Observable<GithubUser> getGithubUserInfo(@Query("q") String name);
}
