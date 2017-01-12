package com.hotbitmapgg.gank.widget;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.hotbitmapgg.studyproject.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemArrowCardView extends RelativeLayout {

  private String name;

  @Bind(R.id.arrow_name)
  TextView mTextView;


  public ItemArrowCardView(Context context) {

    this(context, null);
  }


  public ItemArrowCardView(Context context, AttributeSet attrs) {

    this(context, attrs, 0);
  }


  public ItemArrowCardView(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemArrowCardView);
    name = typedArray.getString(R.styleable.ItemArrowCardView_name);
    typedArray.recycle();

    View view = inflate(context, R.layout.layout_item_arrow_view, this);
    ButterKnife.bind(view);

    init();
  }


  /**
   * 初始化
   */
  private void init() {

    mTextView.setText(name);
  }
}
