package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.model.DoubanMeizi;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.DoubanMeiziDetailsFragment;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;

public class DoubanMeiziPagerActivity extends AbsBaseActivity
{

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private static final String EXTRA_INDEX = "extra_index";

    private static final String EXTRA_TYPE = "extra_type";

    private int currenIndex;

    private Realm realm;

    private int type;

    private RealmResults<DoubanMeizi> doubanMeizis;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_meizi_pager;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        Intent intent = getIntent();
        if (intent != null)
        {
            currenIndex = intent.getIntExtra(EXTRA_INDEX, -1);
            type = intent.getIntExtra(EXTRA_TYPE, -1);
        }

        realm = Realm.getDefaultInstance();
        doubanMeizis = realm.where(DoubanMeizi.class)
                .equalTo("type", type)
                .findAll();

        mViewPager.setAdapter(new MeiziPagerAdapter(getFragmentManager()));
    }

    @Override
    public void initToolBar()
    {

    }

    @Override
    protected void onResume()
    {

        super.onResume();
        mViewPager.setCurrentItem(currenIndex);
    }

    public static void luanch(Activity activity, int index , int type)
    {

        Intent mIntent = new Intent(activity, DoubanMeiziPagerActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(EXTRA_INDEX, index);
        mIntent.putExtra(EXTRA_TYPE, type);
        activity.startActivity(mIntent);
    }


    private class MeiziPagerAdapter extends FragmentStatePagerAdapter
    {

        public MeiziPagerAdapter(FragmentManager fm)
        {

            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return DoubanMeiziDetailsFragment.
                    newInstance(doubanMeizis.get(position).getUrl()
                    , doubanMeizis.get(position).getTitle());
        }


        @Override
        public int getCount()
        {

            return doubanMeizis.size();
        }
    }
}
