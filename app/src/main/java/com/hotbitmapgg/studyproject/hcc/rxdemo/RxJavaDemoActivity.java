package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import butterknife.Bind;

/**
 * Rxjava基本练习
 */
public class RxJavaDemoActivity extends AbsBaseActivity
{

    @Bind(R.id.tv)
    TextView mTv;

    int[] ints = new int[]{1, 2, 3, 4, 5};


    @Override
    public int getLayoutId()
    {
        return R.layout.activity_rx_bus;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        // initData();

    }

//    @Override
//    public void rxBusMessage(String str)
//    {
//
//        LogUtil.all(str);
//    }


    private void initData()
    {
//        Observable.just(ints)
//                .subscribeOn(Schedulers.io())
//                .map(new Func1<int[], String>()
//                {
//                    @Override
//                    public String call(int[] ints)
//                    {
//
//                        return null;
//                    }
//                })
//                .flatMap(new Func1<String, Observable<?>>()
//                {
//                    @Override
//                    public Observable<?> call(String s)
//                    {
//                        return null;
//                    }
//                })
//                .take(3)
//                .debounce(400, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Object>()
//                {
//                    @Override
//                    public void onCompleted()
//                    {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e)
//                    {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o)
//                    {
//
//                    }
//                });
    }

    @Override
    public void initToolBar()
    {

    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
    }

//    @Override
//    protected void initRxBus()
//    {
//        RxBus2.getInstance().toObserverable(Bundle.class)
//                .subscribe(new Action1<Bundle>()
//                {
//
//                    @Override
//                    public void call(Bundle bundle)
//                    {
//
//                        String str = bundle.getString("str");
//                        LogUtil.all(str);
//                    }
//                });
//    }
}
