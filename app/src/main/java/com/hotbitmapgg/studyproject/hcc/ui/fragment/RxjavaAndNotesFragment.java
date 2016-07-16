package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.android_develop_art_explore.AndroidDevelopActivity;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.effective_java.EffectiveJavaNoteActivity;
import com.hotbitmapgg.studyproject.hcc.rxjava_operator.RxJavaOperatorActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.FaceActivity;
import com.hotbitmapgg.studyproject.hcc.widget.ItemArrowCardView;
import com.hotbitmapgg.studyproject.hcc.widget.SpringScrollView;

import butterknife.Bind;
import butterknife.OnClick;

public class RxjavaAndNotesFragment extends LazyFragment
{

    @Bind(R.id.sping_scroll)
    SpringScrollView mScrollView;

    @Bind(R.id.btn_2)
    ItemArrowCardView mRxjavaSample;

    @Bind(R.id.btn_3)
    ItemArrowCardView mRxJavaOperator;

    @Bind(R.id.btn_4)
    ItemArrowCardView mAndroidDevelopArtExplore;

    @Bind(R.id.btn_5)
    ItemArrowCardView mEffectiveJava;


    public static RxjavaAndNotesFragment newInstance()
    {

        return new RxjavaAndNotesFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_rxjava_notes;
    }

    @Override
    public void initViews()
    {

    }


    @OnClick(R.id.btn_2)
    void startRxJavaSample()
    {

        startActivity(new Intent(getActivity(), FaceActivity.class));
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
}
