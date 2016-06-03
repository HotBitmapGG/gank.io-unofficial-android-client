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
import com.hotbitmapgg.studyproject.hcc.ui.fragment.HuaBanMeiziSimpleFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 花瓣妹子接口对应type:
 * 大胸妹=34
 * 小清新=35
 * 文艺范=36
 * 性感妹=37
 * 大长腿=38
 * 黑丝袜=39
 * 小翘臀=40
 */
public class HuaBanMeiziActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> titles = Arrays.asList("大胸妹", "小清新", "文艺范", "性感妹", "大长腿", "黑丝袜", "小翘臀");

    private List<Integer> cids = Arrays.asList(34, 35, 36, 37, 38, 39, 40);


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_huaban_meizi;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mViewPager.setAdapter(new HuaBanMeiziPageAdapter(getFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("没时间解释了,赶紧上车");
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

    private class HuaBanMeiziPageAdapter extends FragmentPagerAdapter
    {

        public HuaBanMeiziPageAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return HuaBanMeiziSimpleFragment.newInstance(cids.get(position), position);
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
