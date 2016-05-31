package com.hotbitmapgg.studyproject.hcc.widget_demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.widget.CustomProgressBar;

import butterknife.Bind;
import butterknife.OnClick;


public class CircleProgressBarActivity extends AbsBaseActivity
{

    @Bind(R.id.progress)
    CustomProgressBar mCustomProgressBar;

    @Bind(R.id.tv_hint)
    TextView mHint;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_progress;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        startAmin();
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("自定义圆形进度条");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    private void startAmin()
    {

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(mCustomProgressBar, "percentage", 1);
        mObjectAnimator.setDuration(2000);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {
                //动画结束后 显示分数
                try
                {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(mHint, "alpha", 0f, 1f);
                    animator.setDuration(1000);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.start();
                    mHint.setVisibility(View.VISIBLE);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }


                super.onAnimationEnd(animation);
            }
        });
        mObjectAnimator.start();
    }

    @OnClick(R.id.tv_hint)
    void onHint()
    {

        mHint.setVisibility(View.GONE);
        startAmin();
    }
}
