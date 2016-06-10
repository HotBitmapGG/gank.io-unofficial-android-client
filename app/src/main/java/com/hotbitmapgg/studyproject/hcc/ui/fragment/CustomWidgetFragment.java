package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.widget_demo.BezaerActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.CircleProgressBarActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.LoveActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.SlideGankMeiziActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.TableActivity;

import butterknife.OnClick;


public class CustomWidgetFragment extends LazyFragment
{


    public static CustomWidgetFragment newInstance()
    {

        return new CustomWidgetFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_widget;
    }

    @OnClick(R.id.widget_1)
    void startLoveAnimLayout()
    {

        startActivity(new Intent(getActivity(), LoveActivity.class));
    }

    @OnClick(R.id.widget_2)
    void startCircleProgress()
    {

        startActivity(new Intent(getActivity(), CircleProgressBarActivity.class));
    }

    @OnClick(R.id.widget_3)
    void startTableView()
    {

        startActivity(new Intent(getActivity(), TableActivity.class));
    }

    @OnClick(R.id.widget_4)
    void startBezaerDemo()
    {

        startActivity(new Intent(getActivity(), BezaerActivity.class));
    }

    @OnClick(R.id.widget_5)
    void startTanTan()
    {

        startActivity(new Intent(getActivity(), SlideGankMeiziActivity.class));
    }


    @Override
    public void initViews()
    {

    }
}
