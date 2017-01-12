package com.hotbitmapgg.gank.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GankPostApi {

  @POST("add2gank")

  /**
   * @Part("url") String url,
   @Part("desc") String title,
   @Part("who") String name,
   @Part("type") String type,
   @Part("debug") boolean isdebug


   @Body GankPostBoby gankPostBoby
   */

  Call<ResponseBody> postGank(@Body RequestBody requestBody);
}
