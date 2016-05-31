package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.os.Bundle;

import com.hotbitmapgg.studyproject.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Rxjava练习
 */
public class RxTestActivity extends RxAppCompatActivity
{

    /**
     * 练习所需的假数据
     */
    final String[] mManyWords = {"Hello", "I", "am", "your", "friend", "Spike"};

    final List<String> mManyWordList = Arrays.asList(mManyWords);


//    @Bind(R.id.tv)
//    TextView mTextView;


    // private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);


        /**
         * 使用Rxlefecycle来管理Activity/Fragment的生命周期
         * 防止内存泄漏
         *
         * 继承RxAppCompatActivity,
         * 添加bindToLifecycle方法管理生命周期.
         * 当页面onPause时, 会自动结束循环线程. 如果注释这句代码, 则会导致内存泄露.
         */

//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(bindToLifecycle())
//                .subscribe(new Action1<Object>()
//                {
//                    @Override
//                    public void call(Object o)
//                    {
//
//                    }
//                });


        /**
         * 用于解除订阅者关系
         */
        //compositeSubscription = new CompositeSubscription();

        /**
         * Rxjava的基础用法
         *
         * 被观察者去订阅观察者
         * 被观察者发出事件 通过订阅 观察者接收事件
         * 可指定事件接收所在的线程 异步 或者同步
         */

//        //创建被观察者  被观察者发出一个事件
//        Observable.OnSubscribe mOnSubscribe = new Observable.OnSubscribe<String>()
//        {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber)
//            {
//
//                subscriber.onNext("发射了一条数据");
//                subscriber.onCompleted();
//            }
//        };
//
//
//        //创建订阅者 其实也是一个观察者 观察被观察者数据发生改变 接收数据
//        Subscriber<String> mStringSubscriber = new Subscriber<String>()
//        {
//            @Override
//            public void onCompleted()
//            {
//
//            }
//
//            @Override
//            public void onError(Throwable e)
//            {
//
//            }
//
//            @Override
//            public void onNext(String s)
//            {
//                Toast.makeText(RxTestActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        };
//
//
//        //被观察者订阅观察者
//
//        //1.创建一个被观察者
//        Observable<String> observable = Observable.create(mOnSubscribe);
//        //2.回调执行在AndroidUI线程
//        observable.observeOn(AndroidSchedulers.mainThread());
//        //3.订阅
//        observable.subscribe(mStringSubscriber);


        /**
         * 常用操作符
         * just: 获取输入数据, 直接分发, 更加简洁, 省略其他回调.
         * from: 获取输入数组, 转变单个元素分发.
         * map: 映射, 对输入数据进行转换, 如大写.
         * flatMap: 增大, 本意就是增肥, 把输入数组映射多个值, 依次分发.
         * reduce: 简化, 正好相反, 把多个数组的值, 组合成一个数据.
         */


//        //该例子是接收一个字符串的list，转成一个个字符串 在转换为大写 并打印出来
//        Observable.just(mManyWordList)
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<List<String>, Observable<String>>()
//                {
//                    @Override
//                    public Observable<String> call(List<String> strings)
//                    {
//                        return Observable.from(strings);
//                    }
//                })
//                .map(new Func1<String, String>()
//                {
//                    @Override
//                    public String call(String s)
//                    {
//                        return s.toUpperCase();
//                    }
//                })
//                .filter(new Func1<String, Boolean>()
//                {
//                    @Override
//                    public Boolean call(String s)
//                    {
//                        return s.length() > 3;
//                    }
//                })
//                .take(3)
//                .reduce(new Func2<String, String, String>()
//                {
//                    @Override
//                    public String call(String s, String s2)
//                    {
//                        return String.format("%s%s" , s , s2);
//                    }
//                })
//                .subscribe(new Action1<String>()
//                {
//                    @Override
//                    public void call(String s)
//                    {
//                        Toast.makeText(RxTestActivity.this, s, Toast.LENGTH_SHORT).show();
//                    }
//                });


        /**
         * Rxjava + Retrofit2进行网络请求操作
         * Api来自代码家的gank.io
         */

//        GankApi gankApi = RetrofitHelper.getGankApi();
//        gankApi.getAndroidDatas(10, 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<Gank, List<Gank.AndroidInfo>>()
//                {
//                    @Override
//                    public List<Gank.AndroidInfo> call(Gank gankAndroid)
//                    {
//                        return gankAndroid.results;
//                    }
//                })
//                .subscribe(new Action1<List<Gank.AndroidInfo>>()
//                {
//                    @Override
//                    public void call(List<Gank.AndroidInfo> androidInfos)
//                    {
//                        LogUtil.all(androidInfos.size() + "");
//                    }
//                }, new Action1<Throwable>()
//                {
//                    @Override
//                    public void call(Throwable throwable)
//                    {
//
//                    }
//                });


        /**
         * 操作符Zip的使用
         *
         * 可以把两个接口返回的数据拼接到一起
         */
//        GankApi gankApi = RetrofitHelper.getGankApi();
//
//        Subscription subscribe = Observable.zip(gankApi.getBeauties(10, 1),
//                gankApi.getAndroidDatas(10, 1),
//                new Func2<GankResult, Gank, List<ZipItem>>()
//                {
//                    @Override
//                    public List<ZipItem> call(GankResult gankResult, Gank gankAndroid)
//                    {
//                        List<ZipItem> zipItems = new ArrayList<ZipItem>();
//                        int size = gankResult.beautys.size();
//                        List<GankResult.GankBeautyBean> beautys = gankResult.beautys;
//                        List<Gank.AndroidInfo> results = gankAndroid.results;
//                        ZipItem zipItem;
//                        for (int i = 0; i < size; i++)
//                        {
//                            GankResult.GankBeautyBean gankBeautyBean = beautys.get(i);
//                            Gank.AndroidInfo androidInfo = results.get(i);
//
//                            zipItem = new ZipItem();
//                            zipItem.desc = androidInfo.desc;
//                            zipItem.description = gankBeautyBean.createdAt;
//                            zipItem.imageUrl = gankBeautyBean.url;
//                            zipItem.who = androidInfo.who;
//
//                            zipItems.add(zipItem);
//                        }
//                        return zipItems;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<ZipItem>>()
//                {
//                    @Override
//                    public void call(List<ZipItem> zipItems)
//                    {
//                        LogUtil.all(zipItems.get(0).desc + "~~~~" + zipItems.get(0).imageUrl + "~~~~~" + zipItems.get(0).who);
//                    }
//                });
//
//        compositeSubscription.add(subscribe);


    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();

//        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed())
//        {
//            compositeSubscription.unsubscribe();
//        }

    }
}
