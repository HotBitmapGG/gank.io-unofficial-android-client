package com.hotbitmapgg.studyproject.hcc.ui.fragment;

import android.content.Intent;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.RxBaseFragment;
import com.hotbitmapgg.studyproject.hcc.notes.android_develop_art_explore.AndroidDevelopActivity;
import com.hotbitmapgg.studyproject.hcc.notes.effective_java.EffectiveJavaNoteActivity;
import com.hotbitmapgg.studyproject.hcc.notes.gcssloop_android_notes.GcsSloopAndroidNotesActivity;
import com.hotbitmapgg.studyproject.hcc.notes.ldtk_blog.LdtkBlogActivity;
import com.hotbitmapgg.studyproject.hcc.notes.learning_notes.LearningNotesActivity;
import com.hotbitmapgg.studyproject.hcc.notes.rxjava_operator.RxJavaOperatorActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;

import butterknife.OnClick;

public class RxjavaAndNotesFragment extends RxBaseFragment
{

    public static RxjavaAndNotesFragment newInstance()
    {

        return new RxjavaAndNotesFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_rxjava_notes;
    }

    @Override
    public void initViews()
    {

    }


    @OnClick(R.id.btn_1)
    void startRxJavaOperator()
    {

        startActivity(new Intent(getActivity(), RxJavaOperatorActivity.class));
    }

    @OnClick(R.id.btn_2)
    void startLearningNotes()
    {

        startActivity(new Intent(getActivity(), LearningNotesActivity.class));
    }


    @OnClick(R.id.btn_3)
    void startGityuanBlog()
    {

        WebActivity.start(getActivity(), "http://gityuan.com/", "GityuanBlog");
    }

    @OnClick(R.id.btn_4)
    void startGcsSloopAndroidNotes()
    {

        startActivity(new Intent(getActivity(), GcsSloopAndroidNotesActivity.class));
    }


    @OnClick(R.id.btn_5)
    void startLdtkBlog()
    {

        startActivity(new Intent(getActivity(), LdtkBlogActivity.class));
    }

    @OnClick(R.id.btn_6)
    void startAndroidDevelopArtExplore()
    {

        startActivity(new Intent(getActivity(), AndroidDevelopActivity.class));
    }

    @OnClick(R.id.btn_7)
    void startEffectiveJava()
    {

        startActivity(new Intent(getActivity(), EffectiveJavaNoteActivity.class));
    }
}
