package com.hotbitmapgg.studyproject.hcc.rxjava_operator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class OperatorAdapter extends AbsRecyclerViewAdapter
{
    private List<Operator> operators = new ArrayList<>();

    public OperatorAdapter(RecyclerView recyclerView, List<Operator> operators)
    {
        super(recyclerView);
        this.operators = operators;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_operator, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {
        if(holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            Glide.with(getContext())
                    .load(operators.get(position).img)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_image)
                    .into(itemViewHolder.img);

            itemViewHolder.name.setText(operators.get(position).name);
            itemViewHolder.desc.setText(operators.get(position).desc);
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {
        return operators.size();
    }

    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {

        public ImageView img;

        public TextView name;

        public TextView desc;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            img = $(R.id.iv_pic);
            name = $(R.id.tv_name);
            desc = $(R.id.tv_desc);
        }

    }
}
