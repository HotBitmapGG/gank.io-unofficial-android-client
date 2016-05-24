package com.hotbitmapgg.studyproject.hcc.recycleview;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewTreeObserver;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.utils.VibratorUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * RecycleView练习 各种用法汇总
 * <p/>
 * 结合Api 以及Rxjava的使用
 *
 * 表格布局
 */
public class RecycleViewDemoFragment_2 extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecycleView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;

    private List<String> datas = Arrays.asList("A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z");
    private ItemTouchHelper itemTouchHelper;

    public static RecycleViewDemoFragment_2 newInstance()
    {
        return new RecycleViewDemoFragment_2();
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_rxjava_1;
    }

    @Override
    public void initViews()
    {

        mRecycleView.setVisibility(View.GONE);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mRefreshLayout.setRefreshing(true);
                loadData();
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                mRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void loadData()
    {
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecycleView.addItemDecoration(new DividerGridItemDecoration());
        RecycleViewDemoAdapter mAdapter = new RecycleViewDemoAdapter(mRecycleView, datas);
        itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecycleView);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addOnItemTouchListener(
                new RecycleViewItemTouchListener(mRecycleView)
                {

                    @Override
                    public void onLongClick(RecyclerView.ViewHolder vh)
                    {
                        itemTouchHelper.startDrag(vh);
                        //设置长按拖拽震动
                        VibratorUtil.vibrator(getActivity(), 100);
                    }

                    @Override
                    public void onItemClick(RecyclerView.ViewHolder vh)
                    {
                        int layoutPosition = vh.getLayoutPosition();
                        Snackbar.make(mRecycleView, datas.get(layoutPosition), Snackbar.LENGTH_SHORT)
                                .setActionTextColor(Color.YELLOW)
                                .show();
                    }
                }
        );


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mRefreshLayout.setRefreshing(false);
                mRecycleView.setVisibility(View.VISIBLE);
            }
        }, 2000);

    }

}
