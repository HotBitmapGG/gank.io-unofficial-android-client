package com.hotbitmapgg.studyproject.hcc.db;

import android.support.annotation.IntDef;

import com.hotbitmapgg.studyproject.hcc.model.GankResult;
import com.hotbitmapgg.studyproject.hcc.model.Item;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class CacheData
{
    public static CacheData mInstance;

    //数据缓存分类 内存
    private static final int DATA_SOURCE_MEMORY = 1;

    //数据缓存分类 磁盘
    private static final int DATA_SOURCE_DISK = 2;

    //从网络获取数据
    private static final int DATA_SOURCE_NETWORK = 3;

    //数据来源
    public int dataSource;

    //空参私有构造函数 防止实例化对象
    private CacheData()
    {

    }

    //获取Cache实例
    public static CacheData getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new CacheData();
        }

        return mInstance;
    }

    //Rxjava用于数据缓存的对象
    BehaviorSubject<List<Item>> cache;


    //安卓中创建枚举合适的方式
    @IntDef({DATA_SOURCE_MEMORY, DATA_SOURCE_DISK, DATA_SOURCE_NETWORK})
    @interface DataSource
    {

    }

    public void setDataSource(int dataSource)
    {
        this.dataSource = dataSource;
    }

    public String getDataSource()
    {
        String dataSourceTextRes;
        switch (dataSource)
        {
            case DATA_SOURCE_MEMORY:
                dataSourceTextRes = "内存";
                break;

            case DATA_SOURCE_DISK:
                dataSourceTextRes = "磁盘";
                break;

            case DATA_SOURCE_NETWORK:
                dataSourceTextRes = "网络";
                break;

            default:
                dataSourceTextRes = "网络";
        }

        return dataSourceTextRes;
    }

    public void loadDataFormNetWork()
    {
        RetrofitHelper.getGankApi().getBeauties(50, 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankResult, List<Item>>()
                {
                    @Override
                    public List<Item> call(GankResult gankResult)
                    {
                        List<GankResult.GankBeautyBean> beautys = gankResult.beautys;
                        List<Item> items = new ArrayList<Item>(beautys.size());
                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                        int size = beautys.size();
                        for (int i = 0; i < size; i++)
                        {
                            Item item = new Item();
                            try
                            {

                                Date date = inputFormat.parse(beautys.get(i).createdAt);
                                item.description = outputFormat.format(date);
                                item.imageUrl = beautys.get(i).url;
                            } catch (ParseException e)
                            {
                                e.printStackTrace();
                            }

                            items.add(item);
                        }

                        return items;
                    }
                })
                .doOnNext(new Action1<List<Item>>()
                {
                    @Override
                    public void call(List<Item> items)
                    {
                        //缓存网络请求数据json到本地
                        DataBaseOpenHelper.mOpenHelper.writeItems(items);
                    }
                })
                .subscribe(new Action1<List<Item>>()
                {
                    @Override
                    public void call(List<Item> items)
                    {
                        cache.onNext(items);
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        LogUtil.all("数据加载失败" + throwable.getMessage());
                    }
                });
    }

    /**
     * Observable 被观察者  observer观察者 subcribe 订阅者
     *
     * @return
     */
    public Subscription subscribeData(Observer<List<Item>> observer)
    {
        if (cache == null)
        {
            //创建缓存
            cache = BehaviorSubject.create();
            Observable.create(new Observable.OnSubscribe<List<Item>>()
            {
                @Override
                public void call(Subscriber<? super List<Item>> subscriber)
                {
                    List<Item> items = DataBaseOpenHelper.getInstance().readItems();
                    //本地缓存数据为空 从网络加载数据
                    if (items == null)
                    {
                        setDataSource(DATA_SOURCE_NETWORK);
                        loadDataFormNetWork();
                    }
                    else
                    {
                        setDataSource(DATA_SOURCE_DISK);
                        subscriber.onNext(items);
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .subscribe(cache);
        }
        else
        {
            setDataSource(DATA_SOURCE_MEMORY);
        }

        return cache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }




}
