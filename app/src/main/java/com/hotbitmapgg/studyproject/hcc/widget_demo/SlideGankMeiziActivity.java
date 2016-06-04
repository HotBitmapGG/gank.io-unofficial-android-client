package com.hotbitmapgg.studyproject.hcc.widget_demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.model.GankResult;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.hotbitmapgg.studyproject.hcc.widget.CircleProgressView;
import com.hotbitmapgg.studyproject.hcc.widget.slidecard.CardDataItem;
import com.hotbitmapgg.studyproject.hcc.widget.slidecard.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class SlideGankMeiziActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.image_slide_panel)
    CardSlidePanel mCardSlidePanel;

    @Bind(R.id.circle_progress)
    CircleProgressView mCircleProgressView;

    @Bind(R.id.root_layout)
    RelativeLayout mRootLayout;

    private int pageNum = 100;

    private int page = 1;

    //滑动的卡片item集合
    private List<CardDataItem> dataList = new ArrayList<>();

    //Gank妹子集合
    private List<GankResult.GankBeautyBean> meizis = new ArrayList<>();

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_slide_card;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        getGankMeizi();

        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener()
        {

            @Override
            public void onShow(int index)
            {

            }

            @Override
            public void onCardVanish(int index, int type)
            {

            }

            @Override
            public void onItemClick(View cardImageView, int index)
            {

            }
        });
    }

    private void getGankMeizi()
    {

        RetrofitHelper.getGankApi().getBeauties(pageNum, page)
                .doOnSubscribe(new Action0()
                {

                    @Override
                    public void call()
                    {

                        showProgress();
                    }
                })
                .map(new Func1<GankResult,List<GankResult.GankBeautyBean>>()
                {

                    @Override
                    public List<GankResult.GankBeautyBean> call(GankResult gankResult)
                    {

                        return gankResult.beautys;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GankResult.GankBeautyBean>>()
                {

                    @Override
                    public void call(List<GankResult.GankBeautyBean> gankBeautyBeen)
                    {

                        meizis.addAll(gankBeautyBeen);
                        finishTask();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("数据加载失败");
                        hideProgress();
                    }
                });
    }

    /**
     * 根据返回的妹子图片
     * 初始化卡片数据
     */
    private void finishTask()
    {

        hideProgress();
        CardDataItem dataItem;
        for (int i = 0; i < meizis.size(); i++)
        {
            dataItem = new CardDataItem();
            dataItem.detailed = meizis.get(i).createdAt;
            dataItem.imgUrl = meizis.get(i).url;
            dataList.add(dataItem);
        }

        mCardSlidePanel.fillData(dataList);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("仿探探滑动卡片效果");
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

        mCircleProgressView.spin();
        mCircleProgressView.setVisibility(View.VISIBLE);
        mRootLayout.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        mCircleProgressView.stopSpinning();
        mCircleProgressView.setVisibility(View.GONE);
        mRootLayout.setVisibility(View.VISIBLE);
    }
}
