package com.hotbitmapgg.studyproject.hcc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.model.ExpressionPackage;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 11 on 2016/4/5.
 */
public class ZhuangbiAdapter extends AbsRecyclerViewAdapter
{
    private List<ExpressionPackage> datas = new ArrayList<>();

    public ZhuangbiAdapter(RecyclerView recyclerView, List<ExpressionPackage> datas)
    {
        super(recyclerView);
        this.datas = datas;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.card_item_zhuangbi, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Glide.with(getContext()).load(datas.get(position).image_url).placeholder(R.drawable.placeholder_image).into(itemViewHolder.mImg);
            itemViewHolder.mTv.setText(datas.get(position).description);
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {
        return datas == null ? 0 : datas.size();
    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {
        @Bind(R.id.item_img)
        ImageView mImg;

        @Bind(R.id.item_tv)
        TextView mTv;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
