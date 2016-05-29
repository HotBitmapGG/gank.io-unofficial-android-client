package com.hotbitmapgg.studyproject.hcc.rxdemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.utils.ACache;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.Arrays;
import java.util.List;
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

    @Bind(R.id.text_date)
    TextView mDate;

    @Bind(R.id.button_test)
    Button mTest;


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


        /**
         * 使用timer做定时操作。当有“x秒后执行y操作”类似的需求的时候，想到使用timer
         *
         * 2秒后输出日志“hello world”，然后结束
         */

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {
                    @Override
                    public void call(Long aLong)
                    {
                        Snackbar.make(mRootlayout, "2秒后执行的操作", Snackbar.LENGTH_SHORT).show();
                    }
                });


        /**
         * 使用interval做周期性操作。当有“每隔xx秒后执行yy操作”类似的需求的时候，
         * 想到使用interval
         *
         * 显示当前系统时间 1秒刷新一次
         */


//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>()
//                {
//                    @Override
//                    public void call(Long aLong)
//                    {
//                        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
//                        String time = format.format(new Date());
//                        mDate.setText(time);
//                    }
//                });


        /**
         * 为按钮添加防抖功能 使用throttleFirst防止按钮重复点击
         *
         * debounce也能达到同样的效果
         */

        RxView.clicks(mTest)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>()
                {
                    @Override
                    public void call(Void aVoid)
                    {
                        LogUtil.all("按钮防抖测试点击" + SystemClock.currentThreadTimeMillis());
                        //RxBus.getInstance().post("hcc");
//                        Bundle bundle = new Bundle();
//                        bundle.putString("str" ,"hcc");
//                        RxBus2.getInstance().post(bundle);
//
//                        startActivity(new Intent(RxJavaSampleActivity.this , RxJavaDemoActivity.class));
                    }
                });


        /**
         * 使用schedulePeriodically做轮询请求
         */
//
//        Observable.create(new Observable.OnSubscribe<String>()
//        {
//            @Override
//            public void call(final Subscriber<? super String> subscriber)
//            {
//                Schedulers.newThread().createWorker().schedulePeriodically(new Action0()
//                {
//                    @Override
//                    public void call()
//                    {
//                        subscriber.onNext("轮询请求事件" + SystemClock.currentThreadTimeMillis());
//                    }
//                }, 3000, 1000, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Action1<String>()
//        {
//            @Override
//            public void call(String s)
//            {
//                LogUtil.all("内容:" + s);
//            }
//        });


        /**
         * 使用RxJava进行数据 集合的遍历
         */

        List<String> strs = Arrays.asList("1", "2", "3", "4", "5");

        int[] ints = new int[]{1, 2, 3, 4, 5, 6};

        Observable.from(strs)
                .observeOn(AndroidSchedulers.mainThread())
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