package com.hotbitmapgg.studyproject.hcc.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hotbitmapgg.studyproject.hcc.base.StudyApp;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


public class RetrofitHelper
{

    public static final String BASE_ZHUANGBI_URL = "http://zhuangbi.info/";

    public static final String BASE_GANK_URL = "http://gank.io/api/";

    private OkHttpClient mOkHttpClient;

    private final GankApi gankApi;

    public static RetrofitHelper builder()
    {

        return new RetrofitHelper();
    }

    private RetrofitHelper()
    {

        initOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //zhuangBiApi = retrofit.create(ZhuangBiApi.class);
        gankApi = retrofit.create(GankApi.class);
    }

    public static GankApi getGankApi()
    {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankApi gankApi = mRetrofit.create(GankApi.class);

        return gankApi;
    }


    /**
     * 初始化OKHttpClient
     */
    private void initOkHttpClient()
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
                    Cache cache = new Cache(new File(StudyApp.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

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

//    public Observable<List<ZhuangBiBean>> getZhuangBi(String key)
//    {
//        return zhuangBiApi.search(key);
//    }

    public Observable<GankResult> getGankBeautyList(int num, int page)
    {

        return gankApi.getBeauties(num, page);
    }
}
