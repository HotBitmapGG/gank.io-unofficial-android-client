package com.hotbitmapgg.studyproject.rank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.rank.base.RxBaseActivity;
import com.hotbitmapgg.studyproject.rank.ui.fragment.AboutFragment;

import butterknife.Bind;

/**
 * Created by hcc on 16/7/9 21:43
 * 100332338@qq.com
 */
public class AboutActivity extends RxBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_about;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        getFragmentManager().beginTransaction().replace(R.id.about, AboutFragment.newInstance()).commit();
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("关于AndroidRank");
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
