package com.hotbitmapgg.gank.ui.activity;

import butterknife.Bind;
import butterknife.OnClick;
import com.hotbitmapgg.gank.adapter.GankAdapter;
import com.hotbitmapgg.gank.base.RxBaseActivity;
import com.hotbitmapgg.gank.config.ConstantUtil;
import com.hotbitmapgg.gank.model.Gank;
import com.hotbitmapgg.gank.network.RetrofitHelper;
import com.hotbitmapgg.gank.utils.SnackbarUtil;
import com.hotbitmapgg.gank.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.gank.widget.recyclehelper.EndlessRecyclerOnScrollListener;
import com.hotbitmapgg.gank.widget.recyclehelper.HeaderViewRecyclerAdapter;
import com.hotbitmapgg.studyproject.R;
import com.jakewharton.rxbinding.view.RxView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by hcc on 16/7/23 13:58
 * 100332338@qq.com
 * <p/>
 * 搜索干货列表界面
 */
public class SearchGankActivity extends RxBaseActivity {

  @Bind(R.id.search_img)
  ImageView mSearch;

  @Bind(R.id.search_edit)
  EditText mEditText;

  @Bind(R.id.recycle)
  RecyclerView mRecyclerView;

  @Bind(R.id.swipe_refresh)
  SwipeRefreshLayout mSwipeRefreshLayout;

  private int page = 1;

  private int pageNum = 10;

  private String type;

  private List<Gank.GankInfo> ganks = new ArrayList<>();

  private GankAdapter mAdapter;

  private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;

  private View footLayout;

  // all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
  private List<String> types = Arrays.asList("all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端",
      "瞎推荐", "App");

  private boolean mIsRefresh = false;


  @Override
  public int getLayoutId() {

    return R.layout.activity_search_gank_list;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

    Intent intent = getIntent();
    if (intent != null) {
      type = intent.getStringExtra(ConstantUtil.EXTRA_TYPE);
    }
    showProgress();
    initRecycleView();
    setRecycleViewScroll();
    search();
  }


  private void search() {

    RxView.clicks(mSearch)
        .throttleFirst(2, TimeUnit.SECONDS)
        .map(aVoid -> mEditText.getText().toString())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {

          if (!types.contains(s)) {
            SnackbarUtil.showMessage(mRecyclerView, "请输入正确的干货类型");
          }
          type = s;
          mSwipeRefreshLayout.postDelayed(() -> {

            mSwipeRefreshLayout.setRefreshing(true);
            page = 1;
            ganks.clear();
            mIsRefresh = true;
            searchGank();
          }, 500);
        });
  }


  private void searchGank() {

    RetrofitHelper.getGankApi()
        .searchGank(type, pageNum, page)
        .compose(this.bindToLifecycle())
        .filter(gank -> !gank.error)
        .map(gank -> gank.results)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .retry()
        .subscribe(gankInfos -> {

          if (gankInfos.size() < pageNum) {
            footLayout.setVisibility(View.GONE);
          }
          ganks.addAll(gankInfos);
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

      Gank.GankInfo gankInfo = ganks.get(position);

      switch (gankInfo.type) {
        case "休息视频":

          VideoWebActivity.launch(SearchGankActivity.this, gankInfo.url);
          break;
        case "福利":
          startFuliActivity(gankInfo, holder);
          break;
        default:

          WebActivity.start(SearchGankActivity.this, gankInfo.url, gankInfo.desc);
          break;
      }
    });
  }


  public void showProgress() {

    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    mSwipeRefreshLayout.postDelayed(() -> {

      mSwipeRefreshLayout.setRefreshing(true);
      mIsRefresh = true;
      searchGank();
    }, 500);

    mSwipeRefreshLayout.setOnRefreshListener(() -> mSwipeRefreshLayout.setRefreshing(false));
  }


  private void initRecycleView() {

    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(SearchGankActivity.this);
    mRecyclerView.setLayoutManager(mLinearLayoutManager);
    mAdapter = new GankAdapter(mRecyclerView, ganks);
    mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
    createFootLayout();
    mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {

      @Override
      public void onLoadMore(int currentPage) {

        page++;
        footLayout.setVisibility(View.VISIBLE);
        searchGank();
      }
    });
  }


  @Override
  public void initToolBar() {

  }


  @Override public void loadData() {

  }


  public static void launch(Activity activity, String type) {

    Intent mIntent = new Intent(activity, SearchGankActivity.class);
    mIntent.putExtra(ConstantUtil.EXTRA_TYPE, type);
    activity.startActivity(mIntent);
  }


  private void createFootLayout() {

    footLayout = LayoutInflater.from(SearchGankActivity.this)
        .inflate(R.layout.load_more_foot_layout, mRecyclerView, false);
    mHeaderViewRecyclerAdapter.addFooterView(footLayout);
    footLayout.setVisibility(View.GONE);
  }


  @OnClick(R.id.search_back)
  void back() {

    onBackPressed();
  }


  public void startFuliActivity(Gank.GankInfo gankInfo, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
    //Activity跳转动画 界面共享元素的使用
    Intent intent = BigImageActivity.launch(SearchGankActivity.this, gankInfo.url,
        gankInfo.desc);
    ActivityOptionsCompat mActivityOptionsCompat;
    if (Build.VERSION.SDK_INT >= 21) {
      mActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
          SearchGankActivity.this, holder.getParentView().findViewById(R.id.item_image),
          ConstantUtil.TRANSIT_PIC);
    } else {
      mActivityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
          holder.getParentView().findViewById(R.id.item_image), 0, 0,
          holder.getParentView().findViewById(R.id.item_image).getWidth(),
          holder.getParentView().findViewById(R.id.item_image).getHeight());
    }

    startActivity(intent, mActivityOptionsCompat.toBundle());
  }


  public void setRecycleViewScroll() {

    mRecyclerView.setOnTouchListener((v, event) -> {

      return mIsRefresh;
    });
  }
}
