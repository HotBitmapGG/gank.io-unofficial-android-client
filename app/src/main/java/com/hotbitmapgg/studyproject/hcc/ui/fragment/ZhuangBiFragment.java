package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.ZhuangbiAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.model.ZhuangBiBean;

import java.util.List;

import butterknife.Bind;

public class ZhuangBiFragment extends LazyFragment
{
    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

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
        getZhuangBiList();
    }

    private void getZhuangBiList()
    {
//        RetrofitHelper.builder().getZhuangBi("装逼")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<ZhuangBiBean>>()
//                {
//                    @Override
//                    public void call(List<ZhuangBiBean> zhuangBiBeans)
//                    {
//                        if (zhuangBiBeans != null && zhuangBiBeans.size() > 0)
//                        {
//                            finishGetZhuangBiList(zhuangBiBeans);
//                        }
//                    }
//                }, new Action1<Throwable>()
//                {
//                    @Override
//                    public void call(Throwable throwable)
//                    {
//
//                    }
//                });
    }

    private void finishGetZhuangBiList(List<ZhuangBiBean> zhuangBiBeans)
    {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ZhuangbiAdapter mAdapter = new ZhuangbiAdapter(getActivity() , zhuangBiBeans);
        mRecyclerView.setAdapter(mAdapter);
    }


}
