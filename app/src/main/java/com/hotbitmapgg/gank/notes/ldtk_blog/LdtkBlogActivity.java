package com.hotbitmapgg.gank.notes.ldtk_blog;

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
 * Created by hcc on 16/7/31 13:20
 * 100332338@qq.com
 * <p/>
 * ldtk的自定义控件相关博客
 * 地址:https://github.com/Idtk/Blog
 */
public class LdtkBlogActivity extends RxBaseActivity {

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.toolbar)
  Toolbar mToolbar;

  private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;

  private List<LdtkBlog> ldtkBlogs;


  @Override
  public int getLayoutId() {

    return R.layout.activity_notes;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    LdtkBlogContents mLdtkBlogContents = new LdtkBlogContents();
    ldtkBlogs = mLdtkBlogContents.fillData();

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    ldtkBlogAdapter mAdapter = new ldtkBlogAdapter(mRecyclerView, ldtkBlogs);
    mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
    createHead();
    mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
    mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {

      @Override
      public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {

        WebActivity.start(LdtkBlogActivity.this,
            ldtkBlogs.get(position).url,
            ldtkBlogs.get(position).title);
      }
    });
  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle("LdtkBlog");
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
    mNotesExplain.setText("文／ldtk（GitHub)\n\n原地址:https://github.com/Idtk/Blog");
  }
}
