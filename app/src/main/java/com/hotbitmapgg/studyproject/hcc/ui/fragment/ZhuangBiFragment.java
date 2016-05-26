package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.ZhuangbiAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.ZhuangBiBean;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.network.ZhuangBiApi;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.utils.ImageUtil;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ZhuangBiFragment extends LazyFragment
{
    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;


    @Bind(R.id.search_btn)
    Button mSearch;

    @Bind(R.id.edit)
    EditText mQueryEdit;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String content;

    private InputMethodManager mInputManager;

    private CompositeSubscription mCompositeSubscription;

    private List<ZhuangBiBean> datas = new ArrayList<>();

    public static ZhuangBiFragment newInstance()
    {
        return new ZhuangBiFragment();
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_zhuangbi;
    }

    @Override
    public void initViews()
    {


        mCompositeSubscription = new CompositeSubscription();

        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark , R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        RxTextView.textChangeEvents(mQueryEdit)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>()
                {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent)
                    {
                        content = textViewTextChangeEvent.text().toString();
                    }
                });
    }


    @OnClick(R.id.search_btn)
    void searchFace()
    {
        if (!TextUtils.isEmpty(content))
        {
            //加载数据
            showProgress();
            //隐藏键盘
            hideKeyBord();
            //清空输入内容
            mQueryEdit.setText("");

        }
    }

    private void getZhuangBiList(String text)
    {
        LogUtil.all(text + "开始请求");

        ZhuangBiApi zhuangBiApi = RetrofitHelper.getZhuangBiApi();

        zhuangBiApi.search(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ZhuangBiBean>>()
                {
                    @Override
                    public void call(List<ZhuangBiBean> zhuangBiBeans)
                    {
                        if (zhuangBiBeans != null && zhuangBiBeans.size() > 0)
                        {
                            datas.clear();
                            datas.addAll(zhuangBiBeans);
                            finishGetZhuangBiList();
                        }
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

    private void finishGetZhuangBiList()
    {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ZhuangbiAdapter mAdapter = new ZhuangbiAdapter(mRecyclerView , datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                LogUtil.all("点击");
                ZhuangBiBean zhuangBiBean = datas.get(position);
                saveImageToGallery(zhuangBiBean);


            }
        });
    }

    private void saveImageToGallery(ZhuangBiBean zhuangBiBean)
    {

        Subscription s = ImageUtil.saveImageAndGetPathObservable(getActivity(), zhuangBiBean.image_url, zhuangBiBean.description)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>()
                {

                    @Override
                    public void call(Uri uri)
                    {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "pic");
                        String msg = String.format("图片已保存至 %s 文件夹", appDir.getAbsolutePath());
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        Toast.makeText(getActivity(), "保存失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(s);
    }



    public void showProgress()
    {
        LogUtil.all(content);

        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
                getZhuangBiList(content);
            }
        });
    }


    /**
     * 隐藏软键盘
     */
    public void hideKeyBord()
    {
        mInputManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onDestroy()
    {

        if(mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed())
        {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
