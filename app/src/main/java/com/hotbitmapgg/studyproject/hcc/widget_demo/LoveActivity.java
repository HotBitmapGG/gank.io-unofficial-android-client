package com.hotbitmapgg.studyproject.hcc.widget_demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.widget.lovelayout.LoveLikeLayout;

import butterknife.Bind;

public class LoveActivity extends AbsBaseActivity
{

    @Bind(R.id.love)
    LoveLikeLayout mLoveLikeLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_widget_love;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mLoveLikeLayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                mLoveLikeLayout.addLove();
            }
        });
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("爱心点赞效果");
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
}
