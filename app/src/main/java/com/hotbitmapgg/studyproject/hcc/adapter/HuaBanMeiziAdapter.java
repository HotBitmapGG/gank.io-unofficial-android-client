package com.hotbitmapgg.studyproject.hcc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.model.HuaBanMeiziInfo;
import com.hotbitmapgg.studyproject.hcc.recycleview.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget.image.RatioImageView;

import java.util.ArrayList;
import java.util.List;


public class HuaBanMeiziAdapter extends AbsRecyclerViewAdapter
{

    private List<HuaBanMeiziInfo> meiziList = new ArrayList<>();

    public HuaBanMeiziAdapter(RecyclerView recyclerView, List<HuaBanMeiziInfo> meiziList)
    {

        super(recyclerView);
        this.meiziList = meiziList;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_huaban_meizi, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mTextView.setText(meiziList.get(position).getTitle());
            Glide.with(getContext())
                    .load(meiziList.get(position).getThumb())
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

        return meiziList.size();
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
            mTextView = $(R.id.item_title);
            ratioImageView.setOriginalSize(50, 50);
        }
    }
}
