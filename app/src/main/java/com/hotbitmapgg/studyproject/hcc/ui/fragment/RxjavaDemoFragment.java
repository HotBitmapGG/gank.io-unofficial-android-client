package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.android_develop_art_explore.AndroidDevelopActivity;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.effective_java.EffectiveJavaNoteActivity;
import com.hotbitmapgg.studyproject.hcc.rxdemo.RxJavaSampleActivity;
import com.hotbitmapgg.studyproject.hcc.rxjava_operator.RxJavaOperatorActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.DoubanMeiziActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.GankMeiziActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.HuaBanMeiziActivity;
import com.hotbitmapgg.studyproject.hcc.widget.ItemArrowCardView;
import com.hotbitmapgg.studyproject.hcc.widget.SpringScrollView;

import butterknife.Bind;
import butterknife.OnClick;

public class RxjavaDemoFragment extends LazyFragment
{

    @Bind(R.id.sping_scroll)
    SpringScrollView mScrollView;

    @Bind(R.id.btn_1)
    ItemArrowCardView mHuaBanMeizi;

    @Bind(R.id.btn_2)
    ItemArrowCardView mRxjavaSample;

    @Bind(R.id.btn_3)
    ItemArrowCardView mRxJavaOperator;

    @Bind(R.id.btn_4)
    ItemArrowCardView mAndroidDevelopArtExplore;

    @Bind(R.id.btn_5)
    ItemArrowCardView mEffectiveJava;

    @Bind(R.id.btn_6)
    ItemArrowCardView mDoubanMeizi;

    @Bind(R.id.btn_7)
    ItemArrowCardView mGankMeizi;

    public static RxjavaDemoFragment newInstance()
    {

        return new RxjavaDemoFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_rxjava_demo;
    }

    @Override
    public void initViews()
    {

    }

    @OnClick(R.id.btn_1)
    void startHuaBanMeizi()
    {

        startActivity(new Intent(getActivity(), HuaBanMeiziActivity.class));
    }

    @OnClick(R.id.btn_2)
    void startRxJavaSample()
    {

        startActivity(new Intent(getActivity(), RxJavaSampleActivity.class));
    }


    @OnClick(R.id.btn_3)
    void startRxJavaOperator()
    {

        startActivity(new Intent(getActivity(), RxJavaOperatorActivity.class));
    }

    @OnClick(R.id.btn_4)
    void startAndroidDevelopArtExplore()
    {

        startActivity(new Intent(getActivity(), AndroidDevelopActivity.class));
    }

    @OnClick(R.id.btn_5)
    void startEffectiveJava()
    {

        startActivity(new Intent(getActivity(), EffectiveJavaNoteActivity.class));
    }

    @OnClick(R.id.btn_6)
    void startDoubanMeizi()
    {

        startActivity(new Intent(getActivity(), DoubanMeiziActivity.class));
    }

    @OnClick(R.id.btn_7)
    void startGankMeizi()
    {

        startActivity(new Intent(getActivity(), GankMeiziActivity.class));
    }
}
