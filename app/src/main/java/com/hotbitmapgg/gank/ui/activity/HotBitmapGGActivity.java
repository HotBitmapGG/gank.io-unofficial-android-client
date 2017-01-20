package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.studyproject.R;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HotBitmapGGActivity extends RxBaseActivity {

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  @Bind(R.id.collapsing_toolbar)
  CollapsingToolbarLayout mCollapsingToolbarLayout;


  @Override
  public int getLayoutId() {

    return R.layout.activity_hotbitmapgg;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

  }


  @Override
  public void initToolBar() {

    setSupportActionBar(mToolbar);
    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    mCollapsingToolbarLayout.setTitle("关于我");
  }


  @Override public void loadData() {

  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }
}
