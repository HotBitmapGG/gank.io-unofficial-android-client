package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewTreeObserver;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.HuaBanMeiziAdapter;
import com.hotbitmapgg.studyproject.hcc.base.ConstantUtil;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.HuaBanMeizi;
import com.hotbitmapgg.studyproject.hcc.model.HuaBanMeiziInfo;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.ui.activity.HuaBanMeiziDetailsActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class HuaBanMeiziSimpleFragment extends LazyFragment
{


    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int PRELOAD_SIZE = 6;

    public static final String EXTRA_CID = "extra_cid";

    public static final String EXTRA_TYPE = "extra_type";

    private boolean mIsLoadMore = true;

    private int cid;

    private int pageNum = 20;

    private int page = 1;

    private HuaBanMeiziAdapter mAdapter;

    private StaggeredGridLayoutManager mLayoutManager;

    private int type;

    private List<HuaBanMeiziInfo> meiziInfos = new ArrayList<>();


    public static HuaBanMeiziSimpleFragment newInstance(int cid, int type)
    {

        HuaBanMeiziSimpleFragment mHuaBanMeiziSimpleFragment = new HuaBanMeiziSimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CID, cid);
        bundle.putInt(EXTRA_TYPE, type);
        mHuaBanMeiziSimpleFragment.setArguments(bundle);

        return mHuaBanMeiziSimpleFragment;
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_huaban_meizi;
    }

    @Override
    public void initViews()
    {
        cid = getArguments().getInt(EXTRA_CID);
        type = getArguments().getInt(EXTRA_TYPE);

        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {

                mSwipeRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
                getHuaBanMeizi();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {

                page = 1;
                getHuaBanMeizi();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(OnLoadMoreListener(mLayoutManager));
        mAdapter = new HuaBanMeiziAdapter(mRecyclerView, meiziInfos);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getHuaBanMeizi()
    {


        RetrofitHelper.getHuaBanMeiziApi()
                .getHuaBanMeizi(pageNum + "", page + "",
                        ConstantUtil.APP_ID, cid + "",
                        ConstantUtil.APP_SIGN)
                .map(new Func1<ResponseBody,HuaBanMeizi>()
                {

                    @Override
                    public HuaBanMeizi call(ResponseBody responseBody)
                    {

                        try
                        {
                            String string = responseBody.string();

                            return HuaBanMeizi.createFromJson(string);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                            return null;
                        }

                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HuaBanMeizi>()
                {

                    @Override
                    public void call(HuaBanMeizi huaBanMeizi)
                    {
                        meiziInfos.addAll(huaBanMeizi.infos);
                       finishTask();
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

                Intent intent = HuaBanMeiziDetailsActivity.LuanchActivity(getActivity(), meiziInfos.get(position).getThumb(), meiziInfos.get(position).getTitle());
                startActivity(intent);
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
                        getHuaBanMeizi();
                    } else
                    {
                        mIsLoadMore = false;
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy()
    {

        super.onDestroy();
    }
}
