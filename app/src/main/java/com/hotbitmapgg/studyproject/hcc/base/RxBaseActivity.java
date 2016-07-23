package com.hotbitmapgg.studyproject.hcc.base;

import android.os.Bundle;

import com.hotbitmapgg.studyproject.hcc.utils.StatusBarCompatUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract class RxBaseActivity extends RxAppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
        //适配4.4系统状态栏
        StatusBarCompatUtil.compat(this);
    }


    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();
}
