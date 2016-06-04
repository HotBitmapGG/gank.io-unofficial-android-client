package com.hotbitmapgg.studyproject.hcc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.model.ZipItem;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget.image.RatioImageView;

import java.util.ArrayList;
import java.util.List;


public class GankAdapter extends AbsRecyclerViewAdapter
{

    public List<ZipItem> datas = new ArrayList<>();

    private Context context;

    public GankAdapter(RecyclerView recyclerView, List<ZipItem> datas)
    {

        super(recyclerView);
        this.datas = datas;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.card_item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mTextView.setText(datas.get(position).desc);
            Glide.with(getContext())
                    .load(datas.get(position).imageUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_image)
                    .into(itemViewHolder.ratioImageView)
                    .getSize(new SizeReadyCallback()
                    {

                        @Override
                        public void onSizeReady(int width, int height)
                        {

                            if (!itemViewHolder.item.isShown())
                            {
                                itemViewHolder.item.setVisibility(View.VISIBLE);
                            }
                        }
                    });
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

        public RatioImageView ratioImageView;

        public TextView mTextView;

        public View item;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            item = itemView;
            ratioImageView = $(R.id.item_img);
            mTextView = $(R.id.item_tv);
            ratioImageView.setOriginalSize(50, 50);
        }
    }
}
