package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.DoubanSimpleMeiziFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class DoubanMeiziActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> titles = Arrays.asList("大胸妹", "小翘臀", "黑丝袜", "美图控", "高颜值");

    private List<Integer> cids = Arrays.asList(2, 6, 7, 3, 4);


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_douban_meizi;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        initFragments();
    }

    private void initFragments()
    {

        mViewPager.setAdapter(new DoubanMeiziPageAdapter(getFragmentManager()));
        mViewPager.setOffscreenPageLimit(1);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void initToolBar()
    {
        mToolbar.setTitle("没时间解释了,赶紧上车");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private class DoubanMeiziPageAdapter extends FragmentPagerAdapter
    {

        public DoubanMeiziPageAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return DoubanSimpleMeiziFragment.newInstance(cids.get(position),position);
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
