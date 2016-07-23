package com.hotbitmapgg.studyproject.hcc.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hotbitmapgg.studyproject.hcc.AndroidRankApp;
import com.hotbitmapgg.studyproject.hcc.model.GankPostBoby;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper
{

    public static final String BASE_GANK_URL = "http://gank.io/api/";

    public static final String BASE_POST_GANK_URL = "https://gank.io/api/add2gank";

    public static final String BASE_GITHUB_URL = "https://api.github.com/";

    public static final String BASE_GITHUB_LOGIN_URL = "https://github.com/login/oauth/";

    private static OkHttpClient mOkHttpClient;

    static
    {
        initOkHttpClient();
    }

    /**
     * Gank干货Api
     *
     * @return
     */
    public static GankApi getGankApi()
    {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankApi gankApi = mRetrofit.create(GankApi.class);

        return gankApi;
    }

    /**
     * 提交干货Api
     *
     * @return
     */

    public static String getPostGankResult(GankPostBoby boby)
    {

        RequestBody requestBody = new FormBody.Builder()
                .add("url", boby.getUrl())
                .add("desc", boby.getTitle())
                .add("who", boby.getName())
                .add("type", boby.getType())
                .add("debug", boby.getIsdebug())
                .build();
        Request request = new Request.Builder()
                .url(BASE_POST_GANK_URL)
                .post(requestBody)
                .build();
        try
        {
            Response response = mOkHttpClient.newCall(request).execute();

            return response.body().string();
        } catch (IOException e1)
        {
            e1.printStackTrace();

            return null;
        }
    }


    /**
     * GithubApi
     *
     * @return
     */
    public static GithubApi getGithubApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GITHUB_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApi githubApi = retrofit.create(GithubApi.class);

        return githubApi;
    }

    /**
     * Github登录Api
     *
     * @return
     */
    public static GithubApi getGithubLoginApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GITHUB_LOGIN_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApi githubApi = retrofit.create(GithubApi.class);

        return githubApi;
    }


    /**
     * 初始化OKHttpClient
     */
    private static void initOkHttpClient()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null)
        {
            synchronized (RetrofitHelper.class)
            {
                if (mOkHttpClient == null)
                {
                    //设置Http缓存
                    Cache cache = new Cache(new File(AndroidRankApp.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
