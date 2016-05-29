package com.hotbitmapgg.studyproject.hcc.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class AbsBaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        //RxBus第一种实现方式
//        Observable observable = RxBus.getInstance().register(this);
//        observable.observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>()
//                {
//
//                    @Override
//                    public void call(String string)
//                    {
//
//                        LogUtil.all(string);
//                    }
//                });

        //initRxBus();
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
    }


    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        ButterKnife.unbind(this);
        //RxBus.getInstance().unregister(this);
    }

    //public void rxBusMessage(String str){};

   // protected abstract void initRxBus();

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();
}
