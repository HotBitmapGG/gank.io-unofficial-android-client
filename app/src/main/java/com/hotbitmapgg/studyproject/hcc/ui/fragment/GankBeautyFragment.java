package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.GankAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;
import com.hotbitmapgg.studyproject.hcc.model.Item;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
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
import rx.schedulers.Schedulers;

public class GankBeautyFragment extends LazyFragment
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;


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
        //mSwipeRefreshLayout.setRefreshing(true);
        startGetBeautysByMap();

        // startGetBeautysZip();
    }

//    private void startGetBeautysZip()
//    {
//        Observable.zip(RetrofitHelper.builder().getGankBeautyList(50, 1).map(new Func1<GankResult, Observable<?>>()
//        {
//            @Override
//            public Observable<?> call(GankResult gankResult)
//            {
//                return null;
//            }
//        }), RetrofitHelper.builder().getGankBeautyList(50, 1), new Func2<List<Item>, List<ZhuangBiBean>, List<Item>>()
//        {
//            @Override
//            public List<Item> call(List<Item> items, List<ZhuangBiBean> zhuangBiBeans)
//            {
//                return null;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//    }

    private void startGetBeautysByMap()
    {
        RetrofitHelper.builder().getGankBeautyList(30, 1)
              .doOnSubscribe(new Action0()
              {
                  @Override
                  public void call()
                  {
                      mSwipeRefreshLayout.setRefreshing(true);
                  }
              })
                .map(new Func1<GankResult, List<Item>>()
                {
                    @Override
                    public List<Item> call(GankResult gankResult)
                    {
                        List<GankResult.GankBeautyBean> beautys = gankResult.beautys;
                        List<Item> items = new ArrayList<Item>(beautys.size());
                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
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
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Item>>()
                {
                    @Override
                    public void call(List<Item> items)
                    {
                        if (items != null && items.size() > 0)
                        {
                            finishGetBeautys(items);
                        }
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        LogUtil.all("加载数据失败");
                    }
                });
    }

    private void finishGetBeautys(List<Item> items)
    {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        GankAdapter mAdapter = new GankAdapter(getActivity(), items);
        mRecyclerView.setAdapter(mAdapter);
    }
}
