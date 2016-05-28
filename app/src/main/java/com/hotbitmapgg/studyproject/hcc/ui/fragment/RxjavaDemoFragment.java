package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;
import android.widget.Button;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.rxdemo.RxJavaSampleActivity;
import com.hotbitmapgg.studyproject.hcc.widget.SpringScrollView;

import butterknife.Bind;
import butterknife.OnClick;

public class RxjavaDemoFragment extends LazyFragment
{

    @Bind(R.id.sping_scroll)
    SpringScrollView mScrollView;


    @Bind(R.id.btn_1)
    Button mRxjavaBtn;

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
    void startRxjavaDemo()
    {
        startActivity(new Intent(getActivity() , RxJavaSampleActivity.class));
    }
}
