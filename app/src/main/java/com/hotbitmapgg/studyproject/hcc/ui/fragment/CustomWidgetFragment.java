package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.adapter.WidgetAdapter;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget_demo.SlideGankMeiziActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.BezaerActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.CircleProgressBarActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.LoveActivity;
import com.hotbitmapgg.studyproject.hcc.widget_demo.TableActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


public class CustomWidgetFragment extends LazyFragment
{


    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    private List<String> names = Arrays.asList("点赞爱心动画", "自定义圆形进度条", "TableView", "贝塞尔线练习", "GankMeizi");

    public static CustomWidgetFragment newInstance()
    {

        return new CustomWidgetFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_widget;
    }

    @Override
    public void initViews()
    {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        WidgetAdapter mAdapter = new WidgetAdapter(mRecyclerView, names);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                switch (position)
                {
                    case 0:
                        startActivity(new Intent(getActivity(), LoveActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(getActivity(), CircleProgressBarActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getActivity(), TableActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(getActivity(), BezaerActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), SlideGankMeiziActivity.class));
                        break;
                }
            }
        });
    }
}
