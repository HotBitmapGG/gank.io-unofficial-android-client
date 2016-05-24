package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.recycleview.RecycleViewDemoActivity;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.GankBeautyFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.HomeFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.ZhuangBiFragment;

import butterknife.Bind;


public class MainActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    private HomeFragment homeFragment;

    private ZhuangBiFragment zhuangBiFragment;

    private GankBeautyFragment gankBeautyFragment;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {
        if (mNavigationView != null)
        {
            setupDrawerContent(mNavigationView);
        }

        zhuangBiFragment = ZhuangBiFragment.newInstance();
        gankBeautyFragment = GankBeautyFragment.newInstance();
        homeFragment = HomeFragment.newInstance();
        addFragment(gankBeautyFragment);
    }

    @Override
    public void initToolBar()
    {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                          addFragment(gankBeautyFragment);
                        break;
                    case R.id.nav_messages:
                        startActivity(new Intent(MainActivity.this, TestActivity.class));
                        break;
                    case R.id.nav_my_focus:
                        addFragment(homeFragment);
                        //startActivity(new Intent(MainActivity.this, RxTestActivity.class));
                        break;
                    case R.id.nav_foucs_me:
                        startActivity(new Intent(MainActivity.this, RecycleViewDemoActivity.class));
                        break;
                    case R.id.nav_article:
                        addFragment(zhuangBiFragment);
                    case R.id.nav_video:
                        break;
                    case R.id.nav_about:
                        break;
                    default:
                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    /**
     * 添加Fragment
     */
    public void addFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // DailyListFragment dailyListFragment = DailyListFragment.newInstance();
        fragmentTransaction.replace(R.id.conotent, fragment);
        fragmentTransaction.commit();
    }
}
