package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankListAdapter;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.model.Gank;
import com.hotbitmapgg.studyproject.hcc.model.GankDayInfo;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hcc on 16/7/6 15:44
 * 100332338@qq.com
 * <p/>
 * 每日干货数据
 */
public class GankToDayActivity extends AbsBaseActivity
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.iv_fuli)
    ImageView mImageView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private static final String ARG_YEAR = "year";

    private static final String ARG_MONTH = "month";

    private static final String ARG_DAY = "day";

    private int year;

    private int month;

    private int day;

    private List<Gank.GankInfo> ganks = new ArrayList<>();

    private BottomSheetBehavior<RecyclerView> behavior;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_gank_day;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        Intent intent = getIntent();
        if (intent != null)
        {
            year = intent.getIntExtra(ARG_YEAR, -1);
            month = intent.getIntExtra(ARG_MONTH, -1);
            day = intent.getIntExtra(ARG_DAY, -1);
        }

        getGankData();
    }


    private void getGankData()
    {

        RetrofitHelper.getGankApi()
                .getGankDayData(year, month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankDayInfo>()
                {

                    @Override
                    public void call(GankDayInfo gankDayInfo)
                    {

                        if (gankDayInfo.results.Android != null)
                            ganks.addAll(gankDayInfo.results.Android);
                        if (gankDayInfo.results.iOS != null)
                            ganks.addAll(gankDayInfo.results.iOS);
                        if (gankDayInfo.results.休息视频 != null)
                            ganks.addAll(gankDayInfo.results.休息视频);
                        if (gankDayInfo.results.拓展资源 != null)
                            ganks.addAll(gankDayInfo.results.拓展资源);
                        if (gankDayInfo.results.瞎推荐 != null)
                            ganks.addAll(gankDayInfo.results.瞎推荐);
                        if (gankDayInfo.results.福利 != null)
                            ganks.addAll(gankDayInfo.results.福利);

                        if (gankDayInfo.results.福利 != null)
                            setBackBroudImage(gankDayInfo.results.福利.get(0).url);
                        finishTask();
                    }
                });
    }

    private void setBackBroudImage(String url)
    {

        Glide.with(GankToDayActivity.this)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }

    private void finishTask()
    {

        if (ganks.isEmpty())
            new AlertDialog.Builder(GankToDayActivity.this)
                    .setTitle("提示")
                    .setMessage("今日休息,暂无干货")
                    .setCancelable(false)
                    .setNegativeButton("确定", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            dialog.dismiss();
                            GankToDayActivity.this.finish();
                        }
                    }).show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(GankToDayActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        GankListAdapter mAdapter = new GankListAdapter(mRecyclerView, ganks);
        mRecyclerView.setAdapter(mAdapter);

        behavior = BottomSheetBehavior.from(mRecyclerView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
        {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState)
            {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset)
            {

            }
        });

        showBottomSheet();
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle(year + "/" + month + "/" + day);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void showBottomSheet()
    {

        behavior.setPeekHeight(400);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public static void luanhcer(Activity activity, int year, int month, int day)
    {

        Intent intent = new Intent(activity, GankToDayActivity.class);
        intent.putExtra(ARG_YEAR, year);
        intent.putExtra(ARG_MONTH, month);
        intent.putExtra(ARG_DAY, day);
        activity.startActivity(intent);
    }
}
