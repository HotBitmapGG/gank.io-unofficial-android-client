package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.Gank;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.recycleview.loadmore.EndlessRecyclerOnScrollListener;
import com.hotbitmapgg.studyproject.hcc.recycleview.loadmore.HeaderViewRecyclerAdapter;
import com.hotbitmapgg.studyproject.hcc.ui.activity.VideoWebActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GankFragment extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final static String EXTRA_TYPE = "type";

    private int pageNum = 30;

    private int page = 1;

    private List<Gank.GankInfo> infos = new ArrayList<>();

    private GankAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    private String type;

    private View footLayout;

    private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;


    public static GankFragment newInstance(String dataType)
    {

        GankFragment gankFragment = new GankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TYPE, dataType);
        gankFragment.setArguments(bundle);

        return gankFragment;
    }


    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_gank;
    }

    @Override
    public void initViews()
    {

        type = getArguments().getString(EXTRA_TYPE);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {

                mSwipeRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
                startGetBeautysByMap();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                startGetBeautysByMap();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GankAdapter(mRecyclerView, infos);
        mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
        createFootLayout();
        mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager)
        {

            @Override
            public void onLoadMore(int currentPage)
            {
                page++;
                startGetBeautysByMap();
            }
        });


    }


    private void startGetBeautysByMap()
    {

        RetrofitHelper.getGankApi()
                .getGankDatas(type, pageNum, page)
                .filter(new Func1<Gank,Boolean>()
                {

                    @Override
                    public Boolean call(Gank gank)
                    {

                        return !gank.error;
                    }
                })
                .map(new Func1<Gank,List<Gank.GankInfo>>()
                {

                    @Override
                    public List<Gank.GankInfo> call(Gank gank)
                    {

                        return gank.results;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Gank.GankInfo>>()
                {

                    @Override
                    public void call(List<Gank.GankInfo> gankInfos)
                    {

                        if(gankInfos.size() < pageNum)
                         footLayout.setVisibility(View.GONE);
                        infos.addAll(gankInfos);
                        finishTask();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {
                        footLayout.setVisibility(View.GONE);
                        SnackbarUtil.showMessage(mRecyclerView, "数据加载失败,下拉刷新重新加载!");
                        mSwipeRefreshLayout.post(new Runnable()
                        {

                            @Override
                            public void run()
                            {

                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
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

                Gank.GankInfo gankInfo = infos.get(position);

                if (!type.equals("休息视频"))
                {

                    WebActivity.start(getActivity() ,gankInfo.url , gankInfo.desc);
                } else
                {
                    VideoWebActivity.launch(getActivity(), gankInfo.url);
                }
            }
        });
    }


    private void createFootLayout()
    {

        footLayout = LayoutInflater.from(getActivity()).inflate(R.layout.load_more_foot_layout, mRecyclerView, false);
        mHeaderViewRecyclerAdapter.addFooterView(footLayout);
    }

}
