package com.hotbitmapgg.gank.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by hcc on 16/9/20 00:47
 * 100332338@qq.com
 */
public class NoScrollViewPager extends ViewPager {

  public NoScrollViewPager(Context context, AttributeSet attrs) {

    super(context, attrs);
    // TODO Auto-generated constructor stub
  }


  public NoScrollViewPager(Context context) {

    super(context);
  }


  @Override
  public void scrollTo(int x, int y) {

    super.scrollTo(x, y);
  }


  @Override
  public void setCurrentItem(int item, boolean smoothScroll) {

    super.setCurrentItem(item, smoothScroll);
  }


  @Override
  public void setCurrentItem(int item) {

    super.setCurrentItem(item, false);
  }
}
