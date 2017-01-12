package com.hotbitmapgg.gank.ui.fragment;

import butterknife.Bind;
import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.gank.base.RxBaseFragment;
import com.hotbitmapgg.gank.widget.NoScrollViewPager;
import com.hotbitmapgg.studyproject.R;
import java.util.Arrays;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.widget.TextView;

/**
 * 主界面
 * gank.io的Api
 * <p/>
 * 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 */
public class HomeFragment extends RxBaseFragment {

  @Bind(R.id.sliding_tabs)
  SlidingTabLayout mSlidingTabLayout;

  @Bind(R.id.tab_pager)
  NoScrollViewPager mViewPager;

  private List<String> titles = Arrays.asList("all", "Android", "iOS", "App", "前端", "拓展资源", "休息视频",
      "瞎推荐");


  public static HomeFragment newInstance() {

    return new HomeFragment();
  }


  @Override
  public int getLayoutId() {

    return R.layout.fragment_home;
  }


  @Override
  public void initViews() {

    mViewPager.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
    mViewPager.setOffscreenPageLimit(titles.size());
    mSlidingTabLayout.setViewPager(mViewPager);
    measureTabLayoutTextWidth(0);
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }


      @Override
      public void onPageSelected(int position) {

        measureTabLayoutTextWidth(position);
      }


      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }


  public void measureTabLayoutTextWidth(int position) {

    String titleName = titles.get(position);
    TextView titleView = mSlidingTabLayout.getTitleView(position);
    TextPaint paint = titleView.getPaint();
    float v = paint.measureText(titleName);
    mSlidingTabLayout.setIndicatorWidth(v / 3);
  }


  private class TabPagerAdapter extends FragmentStatePagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {

      super(fm);
    }


    @Override
    public Fragment getItem(int position) {

      return GankFragment.newInstance(titles.get(position));
    }


    @Override
    public CharSequence getPageTitle(int position) {

      return titles.get(position);
    }


    @Override
    public int getCount() {

      return titles.size();
    }
  }
}
