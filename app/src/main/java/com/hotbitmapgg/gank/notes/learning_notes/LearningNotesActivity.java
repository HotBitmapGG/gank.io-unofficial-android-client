package com.hotbitmapgg.gank.notes.learning_notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.config.ConstantUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by hcc on 16/7/31 11:22
 * 100332338@qq.com
 * <p/>
 * 面试整理界面
 * GeniusVJR/LearningNotes
 */
public class LearningNotesActivity extends RxBaseActivity {

  @Bind(R.id.tab_layout)
  SlidingTabLayout mSlidingTabLayout;

  @Bind(R.id.viewpager)
  ViewPager mViewPager;

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  private List<String> titles = Arrays.asList(
      "安卓知识",
      "设计模式",
      "JAVA基础",
      "数据结构",
      "网络相关");

  private String[] types = new String[] {
      ConstantUtil.NOTES_TYPE_ANDROID,
      ConstantUtil.NOTES_TYPE_DESIGN_PATTERN,
      ConstantUtil.NOTES_TYPE_JAVA,
      ConstantUtil.NOTES_TYPE_DATA_STRUCTURE,
      ConstantUtil.NOTES_TYPE_NETWORK
  };


  @Override
  public int getLayoutId() {

    return R.layout.activity_learning_notes;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {
    //进入该界面 先把数据存储到数据中
    LearningNotesContents learningNotesContents = new LearningNotesContents();
    learningNotesContents.fillAndroidData();
    learningNotesContents.fillDesignPatternData();
    learningNotesContents.fillJavaData();
    learningNotesContents.fillDataStructrueData();
    learningNotesContents.fillNetworkData();

    mViewPager.setAdapter(new NotesPagerAdapter(getSupportFragmentManager()));
    mSlidingTabLayout.setViewPager(mViewPager);
  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle("LearningNotes");
    setSupportActionBar(mToolbar);
    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }


  private class NotesPagerAdapter extends FragmentStatePagerAdapter {

    public NotesPagerAdapter(FragmentManager fm) {

      super(fm);
    }


    @Override
    public Fragment getItem(int position) {

      return LearningNotesFragment.newInstance(types[position]);
    }


    @Override
    public int getCount() {

      return titles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

      return titles.get(position);
    }
  }
}
