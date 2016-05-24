package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.widget.LoveLikeLayout;

import butterknife.Bind;

public class TestActivity extends AbsBaseActivity
{

//    @Bind(R.id.progress)
//    CustomProgressBar mCustomProgressBar;
//
//    @Bind(R.id.tv_hint)
//    TextView mHint;

//    @Bind(R.id.webView)
//    WebView mWebView;

    @Bind(R.id.love)
    LoveLikeLayout mLoveLikeLayout;


    @Override
    public int getLayoutId()
    {
        return R.layout.activity_progress;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mLoveLikeLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mLoveLikeLayout.addLove();
            }
        });
        //startAmin();
        //mWebView.loadUrl("http://www.workec.com/html/form/Q0FyNWwlMkZOYlRjTSUzRA==.html?uid=2117808");
    }

    @Override
    public void initToolBar()
    {

    }

//    private void startAmin()
//    {
//        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(mCustomProgressBar, "percentage", 1);
//        mObjectAnimator.setDuration(2000);
//        mObjectAnimator.setInterpolator(new LinearInterpolator());
//        mObjectAnimator.addListener(new AnimatorListenerAdapter()
//        {
//            @Override
//            public void onAnimationEnd(Animator animation)
//            {
//                //动画结束后 显示分数
//                try
//                {
//                    ObjectAnimator animator = ObjectAnimator.ofFloat(mHint, "alpha", 0f, 1f);
//                    animator.setDuration(1000);
//                    animator.setInterpolator(new LinearInterpolator());
//                    animator.start();
//                    mHint.setVisibility(View.VISIBLE);
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//
//                super.onAnimationEnd(animation);
//            }
//        });
//        mObjectAnimator.start();
//    }

//    @OnClick(R.id.tv_hint)
//    void onHint()
//    {
//        mHint.setVisibility(View.GONE);
//       startAmin();
//    }
}
