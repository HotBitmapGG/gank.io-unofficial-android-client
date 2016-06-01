package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 主界面
 * gank.io的Api
 * <p/>
 * 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 */
public class HomeFragment extends LazyFragment
{


    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabLayout;

    @Bind(R.id.tab_pager)
    ViewPager mViewPager;


    private List<String> titles = Arrays.asList("Android", "iOS", "前端", "拓展资源", "休息视频","瞎推荐");


    public static HomeFragment newInstance()
    {

        return new HomeFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_home;
    }

    @Override
    public void initViews()
    {

        mViewPager.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    private class TabPagerAdapter extends FragmentStatePagerAdapter
    {

        public TabPagerAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return GankFragment.newInstance(titles.get(position));
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
