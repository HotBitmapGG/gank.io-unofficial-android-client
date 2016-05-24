package com.hotbitmapgg.studyproject.hcc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.model.ZhuangBiBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ZhuangbiAdapter extends RecyclerView.Adapter
{
    public List<ZhuangBiBean> datas = new ArrayList<>();

    private Context context;

    public ZhuangbiAdapter(Context context, List<ZhuangBiBean> datas)
    {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item_zhuangbi, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Glide.with(context).load(datas.get(position).image_url).placeholder(R.mipmap.icon_default_image_down_fail).into(itemViewHolder.mImg);
            itemViewHolder.mTv.setText(datas.get(position).description);
        }
    }

    @Override
    public int getItemCount()
    {
        return datas == null ? 0 : datas.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder
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
