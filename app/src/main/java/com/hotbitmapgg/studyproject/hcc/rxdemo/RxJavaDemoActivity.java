package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.os.Bundle;

import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Rxjava基本练习
 */
public class RxJavaDemoActivity extends AbsBaseActivity
{

    int[] ints = new int[]{1, 2, 3, 4, 5};

    @Override
    public int getLayoutId()
    {
        return 0;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        initData();
    }

    private void initData()
    {
        Observable.just(ints)
                .subscribeOn(Schedulers.io())
                .map(new Func1<int[], String>()
                {
                    @Override
                    public String call(int[] ints)
                    {

                        return null;
                    }
                })
                .flatMap(new Func1<String, Observable<?>>()
                {
                    @Override
                    public Observable<?> call(String s)
                    {
                        return null;
                    }
                })
                .take(3)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(Object o)
                    {

                    }
                });
    }

    @Override
    public void initToolBar()
    {

    }


}
