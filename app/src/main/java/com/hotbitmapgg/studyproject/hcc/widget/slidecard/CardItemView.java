package com.hotbitmapgg.studyproject.hcc.widget.slidecard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.utils.GankMeiziDateUtil;

/**
 * 卡片View项
 *
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends LinearLayout
{

    public ImageView imageView;

    private TextView textView;

    public CardItemView(Context context)
    {

        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle)
    {

        super(context, attrs, defStyle);
        inflate(context, R.layout.card_item, this);
        imageView = (ImageView) findViewById(R.id.card_image_view);
        textView = (TextView) findViewById(R.id.card_text);
    }

    public void fillData(CardDataItem itemData)
    {

        Glide.with(getContext()).
                load(itemData.imgUrl).
                centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).
                into(imageView);

        String time = GankMeiziDateUtil.convertTime(itemData.detailed);
        textView.setText(time);
    }
}
