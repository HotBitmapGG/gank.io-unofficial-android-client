package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.gank.adapter.GankListAdapter;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.Gank;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.R;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by hcc on 16/7/6 15:44
 * 100332338@qq.com
 * <p/>
 * 每日干货数据
 */
public class ToDayGankActivity extends RxBaseActivity {

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.iv_fuli)
  ImageView mImageView;

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  private int year;

  private int month;

  private int day;

  private List<Gank.GankInfo> ganks = new ArrayList<>();

  private BottomSheetBehavior<RecyclerView> behavior;


  @Override
  public int getLayoutId() {

    return R.layout.activity_gank_day;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    Intent intent = getIntent();
    if (intent != null) {
      year = intent.getIntExtra(ConstantUtil.ARG_YEAR, -1);
      month = intent.getIntExtra(ConstantUtil.ARG_MONTH, -1);
      day = intent.getIntExtra(ConstantUtil.ARG_DAY, -1);
    }

    loadData();
  }


  private void setBackGroundImage(String url) {

    Glide.with(ToDayGankActivity.this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(mImageView);
  }


  private void finishTask() {

    if (ganks.isEmpty()) {
      new AlertDialog.Builder(ToDayGankActivity.this)
          .setTitle("提示")
          .setMessage("今日休息,暂无干货")
          .setCancelable(false)
          .setNegativeButton("确定", (dialog, which) -> {

            dialog.dismiss();
            ToDayGankActivity.this.finish();
          }).show();
    }

    LinearLayoutManager layoutManager = new LinearLayoutManager(ToDayGankActivity.this);
    mRecyclerView.setLayoutManager(layoutManager);
    GankListAdapter mAdapter = new GankListAdapter(mRecyclerView, ganks);
    mRecyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener((position, holder) -> {

      Gank.GankInfo gankInfo = ganks.get(position);

      switch (gankInfo.type) {
        case "休息视频":
          VideoWebActivity.launch(ToDayGankActivity.this, gankInfo.url);
          break;
        case "福利":
          Intent intent = BigImageActivity.launch(ToDayGankActivity.this,
              gankInfo.url, gankInfo.desc);
          startActivity(intent);
          break;
        default:

          WebActivity.start(ToDayGankActivity.this, gankInfo.url, gankInfo.desc);
          break;
      }
    });

    behavior = BottomSheetBehavior.from(mRecyclerView);
    behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState) {

      }


      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) {

      }
    });

    showBottomSheet();
  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle(year + "/" + month + "/" + day);
    setSupportActionBar(mToolbar);
    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override public void loadData() {
    RetrofitHelper.getGankApi()
        .getGankDayData(year, month, day)
        .compose(this.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(gankDayInfo -> {

          if (gankDayInfo.results.Android != null) {
            ganks.addAll(gankDayInfo.results.Android);
          }
          if (gankDayInfo.results.iOS != null) {
            ganks.addAll(gankDayInfo.results.iOS);
          }
          if (gankDayInfo.results.休息视频 != null) {
            ganks.addAll(gankDayInfo.results.休息视频);
          }
          if (gankDayInfo.results.拓展资源 != null) {
            ganks.addAll(gankDayInfo.results.拓展资源);
          }
          if (gankDayInfo.results.瞎推荐 != null) {
            ganks.addAll(gankDayInfo.results.瞎推荐);
          }
          if (gankDayInfo.results.福利 != null) {
            ganks.addAll(gankDayInfo.results.福利);
          }

          if (gankDayInfo.results.福利 != null) {
            setBackGroundImage(gankDayInfo.results.福利.get(0).url);
          }
          finishTask();
        });
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }


  private void showBottomSheet() {

    behavior.setPeekHeight(400);
    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
  }


  public static void launch(Activity activity, int year, int month, int day) {

    Intent intent = new Intent(activity, ToDayGankActivity.class);
    intent.putExtra(ConstantUtil.ARG_YEAR, year);
    intent.putExtra(ConstantUtil.ARG_MONTH, month);
    intent.putExtra(ConstantUtil.ARG_DAY, day);
    activity.startActivity(intent);
  }
}
