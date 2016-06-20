package com.hotbitmapgg.studyproject.hcc.ui.activity;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankMeiziAdapter;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.model.GankMeizi;
import com.hotbitmapgg.studyproject.hcc.model.GankMeiziInfo;
import com.hotbitmapgg.studyproject.hcc.model.GankMeiziResult;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.util.List;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GankMeiziActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    private StaggeredGridLayoutManager mLayoutManager;

    private RealmResults<GankMeizi> gankMeizis;

    private int pageNum = 20;

    private int page = 1;

    private static final int PRELOAD_SIZE = 6;

    private boolean mIsLoadMore = true;

    private GankMeiziAdapter mAdapter;

    private Realm realm;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_gank_meizi;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        showProgress();

        realm = Realm.getDefaultInstance();
        gankMeizis = realm.where(GankMeizi.class).findAll();


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(OnLoadMoreListener(mLayoutManager));
        mAdapter = new GankMeiziAdapter(mRecyclerView, gankMeizis);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("Gank妹子福利");
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

    public void showProgress()
    {

        mSwipeRefreshLayout.post(new Runnable()
        {

            @Override
            public void run()
            {

                mSwipeRefreshLayout.setRefreshing(true);
                clearCache();
                getGankMeizi();
            }
        });
    }

    private void clearCache()
    {

        try
        {
            realm.beginTransaction();
            realm.where(GankMeizi.class)
                    .findAll().clear();
            realm.commitTransaction();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getGankMeizi()
    {

        RetrofitHelper.getGankMeiziApi()
                .getGankMeizi(pageNum, page)
                .filter(new Func1<GankMeiziResult,Boolean>()
                {

                    @Override
                    public Boolean call(GankMeiziResult gankMeiziResult)
                    {

                        return !gankMeiziResult.error;
                    }
                })
                .map(new Func1<GankMeiziResult,List<GankMeiziInfo>>()
                {

                    @Override
                    public List<GankMeiziInfo> call(GankMeiziResult gankMeiziResult)
                    {

                        return gankMeiziResult.gankMeizis;
                    }
                })
                .doOnNext(new Action1<List<GankMeiziInfo>>()
                {

                    @Override
                    public void call(List<GankMeiziInfo> gankMeiziInfos)
                    {

                        LogUtil.all("缓存数据");
                        GankMeizi meizi;
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        for (int i = 0; i < gankMeiziInfos.size(); i++)
                        {
                            meizi = new GankMeizi();
                            String url = gankMeiziInfos.get(i).url;
                            String desc = gankMeiziInfos.get(i).desc;
                            meizi.setUrl(url);
                            meizi.setDesc(desc);
                            realm.copyToRealm(meizi);
                        }
                        realm.commitTransaction();
                        realm.close();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GankMeiziInfo>>()
                {

                    @Override
                    public void call(List<GankMeiziInfo> gankMeiziInfos)
                    {

                        LogUtil.all("加载完成");
                        finishTask();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("加载失败" + throwable.getMessage());
                    }
                });
    }


    private void finishTask()
    {

        if (page * pageNum - pageNum - 1 > 0)
            mAdapter.notifyItemRangeChanged(page * pageNum - pageNum - 1, pageNum);
        else
            mAdapter.notifyDataSetChanged();
        if (mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                GankMeiziPageActivity.luanch(GankMeiziActivity.this, position);
            }
        });
    }

    RecyclerView.OnScrollListener OnLoadMoreListener(StaggeredGridLayoutManager layoutManager)
    {

        return new RecyclerView.OnScrollListener()
        {

            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy)
            {

                boolean isBottom = mLayoutManager.findLastCompletelyVisibleItemPositions(
                        new int[2])[1] >= mAdapter.getItemCount() - PRELOAD_SIZE;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom)
                {
                    if (!mIsLoadMore)
                    {
                        mSwipeRefreshLayout.setRefreshing(true);
                        page++;
                        getGankMeizi();
                    } else
                    {
                        mIsLoadMore = false;
                    }
                }
            }
        };
    }
//
//
//    public void hideProgress()
//    {
//
//        mSwipeRefreshLayout.post(new Runnable()
//        {
//
//            @Override
//            public void run()
//            {
//
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }
}
