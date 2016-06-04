package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewTreeObserver;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.Gank;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;
import com.hotbitmapgg.studyproject.hcc.model.Item;
import com.hotbitmapgg.studyproject.hcc.model.ZipItem;
import com.hotbitmapgg.studyproject.hcc.network.GankApi;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.ui.activity.GankDetailsActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.VideoWebActivity;
import com.hotbitmapgg.studyproject.hcc.utils.GankMeiziDateUtil;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
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

    private static final int PRELOAD_SIZE = 6;

    private List<ZipItem> datas = new ArrayList<>();

    private GankAdapter mAdapter;

    private boolean mIsLoadMore = true;

    private StaggeredGridLayoutManager mLayoutManager;

    private String type;


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

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GankAdapter(mRecyclerView, datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(OnLoadMoreListener());
    }


    private void startGetBeautysByMap()
    {

        GankApi gankApi = RetrofitHelper.getGankApi();

        RetrofitHelper.getGankApi().getBeauties(pageNum, page)
                .doOnSubscribe(new Action0()
                {

                    @Override
                    public void call()
                    {

                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                })
                .map(new Func1<GankResult,List<Item>>()
                {

                    @Override
                    public List<Item> call(GankResult gankResult)
                    {

                        List<GankResult.GankBeautyBean> beautys = gankResult.beautys;
                        List<Item> items = new ArrayList<>(beautys.size());
                        int size = beautys.size();
                        Item item;
                        for (int i = 0; i < size; i++)
                        {

                            item = new Item();
                            String time = GankMeiziDateUtil.convertTime(beautys.get(i).createdAt);
                            item.description = time;
                            item.imageUrl = beautys.get(i).url;
                            items.add(item);
                        }
                        return items;
                    }
                })
                .zipWith(gankApi.getGankDatas(type, pageNum, page), new Func2<List<Item>,Gank,List<ZipItem>>()
                {

                    @Override
                    public List<ZipItem> call(List<Item> items, Gank gank)
                    {

                        List<ZipItem> zipItems = new ArrayList<>();
                        ZipItem zipItem;
                        List<Gank.AndroidInfo> results = gank.results;

                        for (int i = 0; i < items.size(); i++)
                        {
                            zipItem = new ZipItem();
                            Item item = items.get(i);
                            Gank.AndroidInfo androidInfo = results.get(i);
                            zipItem.imageUrl = item.imageUrl;
                            zipItem.desc = androidInfo.desc;
                            zipItem.description = item.description;
                            zipItem.who = androidInfo.who;
                            zipItem.url = androidInfo.url;

                            zipItems.add(zipItem);
                        }

                        return zipItems;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ZipItem>>()
                {

                    @Override
                    public void call(List<ZipItem> zipItems)
                    {

                        datas.addAll(zipItems);
                        finishTask();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("数据加载失败");
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

                ZipItem zipItem = datas.get(position);

                if (!type.equals("休息视频"))
                {

                    Intent intent = GankDetailsActivity.start(getActivity(), zipItem.url, zipItem.desc, zipItem.imageUrl, zipItem.who);
                    ActivityOptionsCompat mActivityOptionsCompat;
                    if (Build.VERSION.SDK_INT >= 21)
                    {
                        mActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(), holder.getParentView().findViewById(R.id.item_img), GankDetailsActivity.TRANSIT_PIC);
                    } else
                    {
                        mActivityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                                holder.getParentView().findViewById(R.id.item_img), 0, 0,
                                holder.getParentView().findViewById(R.id.item_img).getWidth(),
                                holder.getParentView().findViewById(R.id.item_img).getHeight());
                    }

                    startActivity(intent, mActivityOptionsCompat.toBundle());
                } else
                {
                    VideoWebActivity.launch(getActivity(), zipItem.url);
                }
            }
        });
    }

    RecyclerView.OnScrollListener OnLoadMoreListener()
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
                        startGetBeautysByMap();
                    } else
                    {
                        mIsLoadMore = false;
                    }
                }
            }
        };
    }
}
