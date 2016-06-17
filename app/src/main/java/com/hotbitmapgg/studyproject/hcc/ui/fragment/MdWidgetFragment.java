package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.recycleview.RecycleViewDemoActivity;

import butterknife.OnClick;


public class MdWidgetFragment extends LazyFragment
{

    public static MdWidgetFragment newInstance()
    {

        return new MdWidgetFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_md;
    }

    @Override
    public void initViews()
    {

    }

    @OnClick(R.id.md_btn_1)
    void startRecycleViewDemo()
    {

        startActivity(new Intent(getActivity(), RecycleViewDemoActivity.class));
    }
}
