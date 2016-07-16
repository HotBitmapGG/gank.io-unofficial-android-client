package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankTypeSelectAdapter;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.AbsRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 提交干货类型选择
 * <p/>
 * Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
 */
public class GankTypeSelectActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    private List<String> types = Arrays.asList("Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App");

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_select_type;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GankTypeSelectAdapter mAdapter = new GankTypeSelectAdapter(mRecyclerView, types);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                String type = types.get(position);
                Intent mIntent = new Intent();
                mIntent.putExtra("type", type);
                setResult(Activity.RESULT_OK, mIntent);
                finish();
            }
        });
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("干货类型");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
