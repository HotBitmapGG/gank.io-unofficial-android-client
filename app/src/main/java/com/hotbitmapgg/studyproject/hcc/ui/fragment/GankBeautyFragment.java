package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewTreeObserver;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.GankAndroid;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;
import com.hotbitmapgg.studyproject.hcc.model.Item;
import com.hotbitmapgg.studyproject.hcc.model.ZipItem;
import com.hotbitmapgg.studyproject.hcc.network.GankApi;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class GankBeautyFragment extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final static String TIME_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    private final static String TIME_FORMAT_2 = "yy/MM/dd HH:mm:ss";

    private int pageNum = 30;

    private int page = 1;

    private static final int PRELOAD_SIZE = 6;

    private List<ZipItem> datas = new ArrayList<>();

    private GankAdapter mAdapter;

    private boolean mIsLoadMore = true;

    private StaggeredGridLayoutManager mLayoutManager;


    public static GankBeautyFragment newInstance()
    {

        return new GankBeautyFragment();
    }


    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_gank;
    }

    @Override
    public void initViews()
    {

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
                        List<Item> items = new ArrayList<Item>(beautys.size());
                        SimpleDateFormat inputFormat = new SimpleDateFormat(TIME_FORMAT_1);
                        SimpleDateFormat outputFormat = new SimpleDateFormat(TIME_FORMAT_2);
                        int size = beautys.size();
                        for (int i = 0; i < size; i++)
                        {
                            try
                            {
                                Item item = new Item();
                                Date date = inputFormat.parse(beautys.get(i).createdAt);
                                String format = outputFormat.format(date);
                                item.description = format;
                                item.imageUrl = beautys.get(i).url;
                                items.add(item);
                            } catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        return items;
                    }
                })
                .zipWith(gankApi.getAndroidDatas(pageNum, page), new Func2<List<Item>,GankAndroid,List<ZipItem>>()
                {

                    @Override
                    public List<ZipItem> call(List<Item> items, GankAndroid gankAndroid)
                    {

                        List<ZipItem> zipItems = new ArrayList<ZipItem>();
                        ZipItem zipItem;
                        List<GankAndroid.AndroidInfo> results = gankAndroid.results;

                        for (int i = 0; i < items.size(); i++)
                        {
                            zipItem = new ZipItem();
                            Item item = items.get(i);
                            GankAndroid.AndroidInfo androidInfo = results.get(i);
                            zipItem.imageUrl = item.imageUrl;
                            zipItem.desc = androidInfo.desc;
                            zipItem.description = item.description;
                            zipItem.who = androidInfo.who;

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
                        mSwipeRefreshLayout.setRefreshing(false);
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
