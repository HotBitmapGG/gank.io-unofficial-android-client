package com.hotbitmapgg.gank.notes.learning_notes;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.gank.base.RxBaseFragment;
import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.utils.LogUtil;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by hcc on 16/7/31 11:33
 * 100332338@qq.com
 */
public class LearningNotesFragment extends RxBaseFragment {

  @Bind(R.id.swipe_refresh)
  SwipeRefreshLayout mSwipeRefreshLayout;

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  private final static String EXTRA_TYPE = "notes_type";

  private String notesType;

  private LearningNotesAdapter mAdapter;


  public static LearningNotesFragment newInstance(String type) {

    LearningNotesFragment mFragment = new LearningNotesFragment();
    Bundle bundle = new Bundle();
    bundle.putString(EXTRA_TYPE, type);
    mFragment.setArguments(bundle);
    return mFragment;
  }


  @Override
  public int getLayoutId() {

    return R.layout.fragment_learning_notes;
  }


  @Override
  public void initViews() {

    notesType = getArguments().getString(EXTRA_TYPE);
    loadData();
  }


  private void loadData() {

    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

      @Override
      public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(false);
      }
    });
    mSwipeRefreshLayout.postDelayed(new Runnable() {

      @Override
      public void run() {
        //加载数据库数据
        Realm.getDefaultInstance()
            .where(LearningNotes.class)
            .equalTo("type", notesType)
            .findAll()
            .asObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<RealmResults<LearningNotes>>() {

              @Override
              public void call(RealmResults<LearningNotes> learningNotes) {

                finishTask(learningNotes);
              }
            }, new Action1<Throwable>() {

              @Override
              public void call(Throwable throwable) {

                LogUtil.all("面试笔记数据加载失败" + throwable.getMessage());
              }
            });
      }
    }, 500);
  }


  private void finishTask(final RealmResults<LearningNotes> learningNotes) {

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mAdapter = new LearningNotesAdapter(mRecyclerView, learningNotes);
    mRecyclerView.setAdapter(mAdapter);
    mSwipeRefreshLayout.setRefreshing(false);

    mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {

      @Override
      public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {

        LearningNotes note = learningNotes.get(position);
        WebActivity.start(getActivity(), note.getUrl(), note.getChapter());
      }
    });
  }
}
