package com.hotbitmapgg.studyproject.hcc.notes.effective_java;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.RxBaseActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * EffectiveJava读书笔记
 * <p/>
 * 数据来自CSND博客,作者:chjttony
 */
public class EffectiveJavaNoteActivity extends RxBaseActivity
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_effective_java;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        EffectiveJavaContents effectiveJavaContents = new EffectiveJavaContents();
        final List<EffectiveJavaNote> effectiveJavaNotes = effectiveJavaContents.fillData();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EffectiveJavaNoteAdapter mAdapter = new EffectiveJavaNoteAdapter(mRecyclerView, effectiveJavaNotes);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                WebActivity.start(EffectiveJavaNoteActivity.this,
                        effectiveJavaNotes.get(position).url,
                        effectiveJavaNotes.get(position).chapter);
            }
        });
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("EffectiveJava读书笔记");
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
