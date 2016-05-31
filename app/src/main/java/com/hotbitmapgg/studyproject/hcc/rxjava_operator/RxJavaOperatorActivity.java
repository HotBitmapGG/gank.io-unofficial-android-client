package com.hotbitmapgg.studyproject.hcc.rxjava_operator;

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
import java.util.List;

import butterknife.Bind;

/**
 * Rxjava操作符使用
 * <p/>
 * 1.RxJava介绍与入门
 * 2.Creating创建操作
 * 3.Transforming变换操作
 * 4.Filtering过滤操作
 * 5.Combining结合操作
 * 6.ErrorHandling错误处理
 * 7.Utility辅助操作
 * 8.Conditional条件和布尔操作
 * 9.Mathematical算术和聚合操作
 * 10.Async异步操作
 * 11.Connect连接操作
 * 12.Blocking阻塞操作
 * 13.String字符串操作
 * 14.其他
 */
public class RxJavaOperatorActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Bind(R.id.tab_layout)
    SlidingTabLayout mTabLayout;

    @Bind(R.id.viewpager)
    ViewPager mVierPager;

    private List<String> operators = new ArrayList<>();

    private int[] ids = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_rx_java_operator;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        initOperators();
        mVierPager.setAdapter(new PagerAdapter(getFragmentManager()));
        mTabLayout.setViewPager(mVierPager);
    }

    private void initOperators()
    {

        operators.add("RxJava");
        operators.add("Creating");
        operators.add("Transforming");
        operators.add("Filtering");
        operators.add("Combining");
        operators.add("ErrorHandling");
        operators.add("Utility");
        operators.add("Conditional");
        operators.add("Mathematical");
        operators.add("Async");
        operators.add("Connect");
        operators.add("Blocking");
        operators.add("String");
        operators.add("Other");
    }

    @Override
    public void initToolBar()
    {

        mToolBar.setTitle("RxjavaDocs");
        setSupportActionBar(mToolBar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);
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

    private class PagerAdapter extends FragmentStatePagerAdapter
    {

        public PagerAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return RxJavaOperatorFragment.newInstance(ids[position]);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {

            return operators.get(position);
        }

        @Override
        public int getCount()
        {

            return operators.size();
        }
    }
}
