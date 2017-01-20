package com.hotbitmapgg.gank.ui.fragment;

import butterknife.Bind;
import com.hotbitmapgg.gank.adapter.GankAdapter;
import com.hotbitmapgg.gank.base.RxBaseFragment;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.Gank;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.ui.activity.BigImageActivity;
import com.hotbitmapgg.gank.ui.activity.VideoWebActivity;
import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.utils.SnackbarUtil;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.gank.widget.recyclehelper.EndlessRecyclerOnScrollListener;
import com.hotbitmapgg.gank.widget.recyclehelper.HeaderViewRecyclerAdapter;
import com.hotbitmapgg.studyproject.R;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class GankFragment extends RxBaseFragment {

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.swipe_refresh)
  SwipeRefreshLayout mSwipeRefreshLayout;

  private int pageNum = 30;

  private int page = 1;

  private List<Gank.GankInfo> infos = new ArrayList<>();

  private GankAdapter mAdapter;

  private String type;

  private View footLayout;

  private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;

  private boolean mIsRefresh = false;


  public static GankFragment newInstance(String dataType) {

    GankFragment gankFragment = new GankFragment();
    Bundle bundle = new Bundle();
    bundle.putString(ConstantUtil.EXTRA_TYPE, dataType);
    gankFragment.setArguments(bundle);

    return gankFragment;
  }


  @Override
  public int getLayoutId() {

    return R.layout.fragment_gank;
  }


  @Override
  public void initViews() {

    type = getArguments().getString(ConstantUtil.EXTRA_TYPE);
    initRefreshLayout();
    initRecyclerView();
  }


  private void initRecyclerView() {
    setRecycleViewScroll();
    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mAdapter = new GankAdapter(mRecyclerView, infos);
    mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
    createFootLayout();
    mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {

      @Override
      public void onLoadMore(int currentPage) {

        page++;
        footLayout.setVisibility(View.VISIBLE);
        loadData();
      }
    });
  }


  private void initRefreshLayout() {
    mSwipeRefreshLayout.setOnRefreshListener(() -> {
      page = 1;
      infos.clear();
      mIsRefresh = true;
      loadData();
    });

    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    mSwipeRefreshLayout.postDelayed(() -> {

      mSwipeRefreshLayout.setRefreshing(true);
      mIsRefresh = true;
      loadData();
    }, 500);
  }


  @Override public void loadData() {
    RetrofitHelper.getGankApi()
        .getGankDatas(type, pageNum, page)
        .compose(this.bindToLifecycle())
        .filter(gank -> !gank.error)
        .map(gank -> gank.results)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(gankInfos -> {

          if (gankInfos.size() < pageNum) {
            footLayout.setVisibility(View.GONE);
          }
          gankInfos.addAll(infos);
          finishTask();
        }, throwable -> {

          if (footLayout != null) {
            footLayout.setVisibility(View.GONE);
          }
          SnackbarUtil.showMessage(mRecyclerView, "数据加载失败,下拉刷新重新加载!");
          mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
        });
  }



  private void finishTask() {

    if (page * pageNum - pageNum - 1 > 0) {
      mAdapter.notifyItemRangeChanged(page * pageNum - pageNum - 1, pageNum);
    } else {
      mAdapter.notifyDataSetChanged();
    }
    if (mSwipeRefreshLayout.isRefreshing()) {
      mSwipeRefreshLayout.setRefreshing(false);
    }
    mIsRefresh = false;
    mAdapter.setOnItemClickListener((position, holder) -> {

      Gank.GankInfo gankInfo = infos.get(position);

      switch (gankInfo.type) {
        case "休息视频":

          VideoWebActivity.launch(getActivity(), gankInfo.url);
          break;
        case "福利":
          launch(gankInfo, holder);
          break;
        default:

          WebActivity.launch(getActivity(), gankInfo.url, gankInfo.desc);
          break;
      }
    });
  }


  public void launch(Gank.GankInfo gankInfo, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
    //Activity跳转动画 界面共享元素的使用
    Intent intent = BigImageActivity.launch(getActivity(), gankInfo.url, gankInfo.desc);
    ActivityOptionsCompat mActivityOptionsCompat;
    if (Build.VERSION.SDK_INT >= 21) {
      mActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
          getActivity(), holder.getParentView().findViewById(R.id.item_image),
          ConstantUtil.TRANSIT_PIC);
    } else {
      mActivityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
          holder.getParentView().findViewById(R.id.item_image), 0, 0,
          holder.getParentView().findViewById(R.id.item_image).getWidth(),
          holder.getParentView().findViewById(R.id.item_image).getHeight());
    }

    startActivity(intent, mActivityOptionsCompat.toBundle());
  }


  private void createFootLayout() {

    footLayout = LayoutInflater.from(getActivity())
        .inflate(R.layout.load_more_foot_layout, mRecyclerView, false);
    mHeaderViewRecyclerAdapter.addFooterView(footLayout);
    footLayout.setVisibility(View.GONE);
  }


  public void setRecycleViewScroll() {

    mRecyclerView.setOnTouchListener((v, event) -> mIsRefresh);
  }
}
