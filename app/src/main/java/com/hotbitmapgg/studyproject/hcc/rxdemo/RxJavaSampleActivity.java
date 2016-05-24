package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.utils.ACache;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * 该类集合各种Rxjava的实际应用
 */
public class RxJavaSampleActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Bind(R.id.root)
    LinearLayout mRootlayout;

    @Bind(R.id.edit_text)
    EditText mEditText;

    @Bind(R.id.edit_text_1)
    EditText mEditText_1;

    @Bind(R.id.edit_text_2)
    EditText mEditText_2;

    @Bind(R.id.edit_text_3)
    EditText mEditText_3;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_rxjava_demo;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        /**
         * Schedulers线程调度器的使用
         *
         * subscribeOn(Schedulers.io()) 会指定之前的操作在io线程执行
         * observeOn(AndroidSchedulers.mainThread()) 会指定下边的操作在AndroidUI线程执行
         */
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>()
                {
                    @Override
                    public void call(Integer integer)
                    {
                        LogUtil.all(integer.toString());
                    }
                });


        /**
         * 使用Debounce
         * 用简单的话讲就是当N个结点发生的时间太靠近（即发生的时间差小于设定的值T），
         * debounce就会自动过滤掉前N-1个结点。
         * <p/>
         * 比如在做百度地址联想的时候，
         * 可以使用debounce减少频繁的网络请求。
         * 避免每输入（删除）一个字就做一次联想
         *
         */


        RxTextView.textChangeEvents(mEditText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>()
                {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent)
                    {
                        LogUtil.all(textViewTextChangeEvent.text().toString());
                    }
                });


        /**
         * 使用combineLatest合并最近N个结点
         * <p/>
         * 注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮。
         */
        Observable<CharSequence> observable_1 = RxTextView.textChanges(mEditText_1).skip(1);
        Observable<CharSequence> observable_2 = RxTextView.textChanges(mEditText_2).skip(1);
        Observable<CharSequence> observable_3 = RxTextView.textChanges(mEditText_3).skip(1);

        Observable.combineLatest(observable_1, observable_2, observable_3, new Func3<CharSequence, CharSequence, CharSequence, Boolean>()
        {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3)
            {

                boolean one = !TextUtils.isEmpty(charSequence);
                boolean two = !TextUtils.isEmpty(charSequence2);
                boolean three = !TextUtils.isEmpty(charSequence3);

                return one && two && three;
            }
        }).subscribe(new Action1<Boolean>()
        {
            @Override
            public void call(Boolean aBoolean)
            {
                if (aBoolean)
                {
                    LogUtil.all("注册成功");
                    Snackbar.make(mRootlayout, "注册成功", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    LogUtil.all("注册失败");
                    Snackbar.make(mRootlayout, "注册失败", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        /**
         * 使用concat来实现网络3层缓存请求数据
         *
         * 依次检查内存 缓存 网络，
         *
         * 内存不为空 会先加载内存的数据
         * 内存为空 会在检查硬盘数据 不为空直接加载
         * 为空会从网络在拉取
         */

        //模拟缓存
        final String mempryCache = "memory";

        final String diskCache = "disk";

        ACache.get(RxJavaSampleActivity.this).put("cache", diskCache);

        Observable<String> memory = Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                if (!TextUtils.isEmpty(mempryCache))
                {
                    subscriber.onNext(mempryCache);
                }
                else
                {
                    subscriber.onCompleted();
                }
            }
        });


        Observable<String> disk = Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                String cache = ACache.get(RxJavaSampleActivity.this).getAsString("cache");
                if (!TextUtils.isEmpty(cache))
                {
                    subscriber.onNext(cache);
                }
                else
                {
                    subscriber.onCompleted();
                }
            }
        });

        Observable<String> network = Observable.just("network");

        Observable.concat(memory, disk, network)
                .first()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>()
                {
                    @Override
                    public void call(String s)
                    {
                        LogUtil.all(s);
                    }
                });






    }


    @Override
    public void initToolBar()
    {

        mToolBar.setTitle("RxJavaDemo");
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

}
