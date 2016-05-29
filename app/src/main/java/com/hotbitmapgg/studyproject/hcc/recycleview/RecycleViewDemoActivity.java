package com.hotbitmapgg.studyproject.hcc.recycleview;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class RecycleViewDemoActivity extends AbsBaseActivity
{


    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Bind(R.id.tab_layout)
    SlidingTabLayout mTabLayout;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private List<String> titles = Arrays.asList("测试1", "测试2");

    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutId()
    {
        return R.layout.activity_recycle;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {
        fragments.add(RecycleViewDemoFragment_1.newInstance());
        fragments.add(RecycleViewDemoFragment_2.newInstance());


        mViewPager.setAdapter(new SamplePagerAdapter(getFragmentManager()));
        mTabLayout.setViewPager(mViewPager);

    }

    @Override
    public void initToolBar()
    {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("RecycleView各种用法");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private class SamplePagerAdapter extends FragmentStatePagerAdapter
    {
        public SamplePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles.get(position);
        }

        @Override
        public int getCount()
        {
            return titles.size();
        }
    }
}
