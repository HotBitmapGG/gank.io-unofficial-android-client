package com.hotbitmapgg.gank.notes.android_develop_art_explore;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.widget.recyclehelper.HeaderViewRecyclerAdapter;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.gank.widget.recyclehelper.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * 安卓开发艺术探索读书笔记
 * <p/>
 * 数据来自 简书作者:HuDP
 */
public class AndroidDevelopActivity extends RxBaseActivity {

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;


  @Override
  public int getLayoutId() {

    return R.layout.activity_notes;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    AndroidDevelopNoteContents androidDevelopNoteContents = new AndroidDevelopNoteContents();
    final List<AndroidDevelopNote> androidDevelopNotes = androidDevelopNoteContents.fillData();

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    AndroidDevelopNotesAdapter mAdapter = new AndroidDevelopNotesAdapter(mRecyclerView,
        androidDevelopNotes);
    mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
    mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
    createHead();
    mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {

      @Override
      public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {

        WebActivity.start(AndroidDevelopActivity.this,
            androidDevelopNotes.get(position).url,
            androidDevelopNotes.get(position).name);
      }
    });
  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle("安卓开发艺术探索读书笔记");
    setSupportActionBar(mToolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }


  public void createHead() {

    View headView = LayoutInflater.from(this)
        .inflate(R.layout.layout_notes_head, mRecyclerView, false);
    mHeaderViewRecyclerAdapter.addHeaderView(headView);
    TextView mNotesExplain = (TextView) headView.findViewById(R.id.notes_explain);
    mNotesExplain.setText("文／HuDP（简书作者)\n\n原文链接:http://www.jianshu.com/p/eb3247fac29a");
  }
}
