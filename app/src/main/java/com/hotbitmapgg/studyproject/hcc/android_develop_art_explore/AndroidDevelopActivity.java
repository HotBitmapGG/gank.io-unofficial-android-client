package com.hotbitmapgg.studyproject.hcc.android_develop_art_explore;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * 安卓开发艺术探索读书笔记
 * <p/>
 * 数据来自 简书作者:HuDP
 */
public class AndroidDevelopActivity extends AbsBaseActivity
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_android_develop_notes;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        AndroidDevelopNoteContents androidDevelopNoteContents = new AndroidDevelopNoteContents();
        final List<AndroidDevelopNote> androidDevelopNotes = androidDevelopNoteContents.fillData();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AndroidDevelopNotesAdapter mAdapter = new AndroidDevelopNotesAdapter(mRecyclerView, androidDevelopNotes);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                WebActivity.start(AndroidDevelopActivity.this,
                        androidDevelopNotes.get(position).url,
                        androidDevelopNotes.get(position).name);
            }
        });
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("安卓开发艺术探索读书笔记");
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
}
