package com.hotbitmapgg.studyproject.hcc.rxjava_operator;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.LazyFragment;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Rxjava操作符介绍
 * 方便查阅
 * 所有数据均来自 RxJavaDocs 中文文档
 */
public class RxJavaOperatorFragment extends LazyFragment
{


    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    public static final String EXTRA_TYPE = "type";

    private int type;

    private List<Operator> datas = new ArrayList<>();

    private DataUtils dataUtils;

    public static RxJavaOperatorFragment newInstance(int type)
    {
        RxJavaOperatorFragment fragment = new RxJavaOperatorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TYPE, type);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_operator;
    }

    @Override
    public void initViews()
    {
        type = getArguments().getInt(EXTRA_TYPE);
        dataUtils = new DataUtils();
        switch (type)
        {
            case 1:
                //Rxjava介绍与入门
                List<Operator> introduceList = dataUtils.getIntroduceList();
                datas.addAll(introduceList);
                LogUtil.all(datas.get(0).name);
                break;

            case 2:
                //创建
                List<Operator> creatingList = dataUtils.getCreatingList();
                datas.addAll(creatingList);
                LogUtil.all(datas.get(0).name);
                break;

            case 3:
                //变换
                List<Operator> transformList = dataUtils.getTransformList();
                datas.addAll(transformList);
                LogUtil.all(datas.get(0).name);
                break;

            case 4:
                //过滤
                List<Operator> filterList = dataUtils.getFilterList();
                datas.addAll(filterList);
                LogUtil.all(datas.get(0).name);
                break;

            case 5:
                //结合
                List<Operator> combinList = dataUtils.getCombinList();
                datas.addAll(combinList);
                break;

            case 6:
                //错误处理
                List<Operator> errorList = dataUtils.getErrorList();
                datas.addAll(errorList);
                break;

            case 7:
                //辅助
                List<Operator> utilityList = dataUtils.getUtilityList();
                datas.addAll(utilityList);
                break;

            case 8:
                //条件与布尔
                List<Operator> conditionalList = dataUtils.getConditionalList();
                datas.addAll(conditionalList);
                break;

            case 9:
                //算术与聚合
                List<Operator> mathList = dataUtils.getMathList();
                datas.addAll(mathList);
                break;

            case 10:
                //异步
                List<Operator> asyncList = dataUtils.getAsyncList();
                datas.addAll(asyncList);
                break;

            case 11:
                //连接
                List<Operator> connectList = dataUtils.getConnectList();
                datas.addAll(connectList);
                break;

            case 12:
                //阻塞
                List<Operator> blockList = dataUtils.getBlockList();
                datas.addAll(blockList);
                break;

            case 13:
                //字符串
                List<Operator> stringList = dataUtils.getStringList();
                datas.addAll(stringList);
                break;

            case 14:
                //其他
                List<Operator> othersList = dataUtils.getOthersList();
                datas.addAll(othersList);
                break;


            default:
                break;
        }

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OperatorAdapter mAdapter = new OperatorAdapter(mRecyclerView, datas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {
                Operator operator = datas.get(position);
                WebActivity.start(getActivity(),operator.url,operator.name);

            }
        });

    }
}
