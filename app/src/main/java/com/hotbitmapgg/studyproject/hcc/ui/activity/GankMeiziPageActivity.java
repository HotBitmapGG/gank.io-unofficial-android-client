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
import com.hotbitmapgg.studyproject.hcc.model.GankMeizi;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.GankMeiziDetailsFragment;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;

public class GankMeiziPageActivity extends AbsBaseActivity
{

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private static final String EXTRA_INDEX = "extra_index";

    private int currenIndex;

    private Realm realm;

    private RealmResults<GankMeizi> gankMeizis;


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
        }

        realm = Realm.getDefaultInstance();
        gankMeizis = realm.where(GankMeizi.class).findAll();

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

    public static void luanch(Activity activity, int index)
    {

        Intent mIntent = new Intent(activity, GankMeiziPageActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(EXTRA_INDEX, index);
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

            return GankMeiziDetailsFragment.newInstance(gankMeizis.get(position).getUrl(), gankMeizis.get(position).getDesc());
        }


        @Override
        public int getCount()
        {

            return gankMeizis.size();
        }
    }
}
