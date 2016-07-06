package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.config.ConstantUtil;
import com.hotbitmapgg.studyproject.hcc.model.GitHubUserInfo;
import com.hotbitmapgg.studyproject.hcc.rxdemo.RxBus2;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.CustomWidgetFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.ExpressionPackageFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.HomeFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.MdWidgetFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.RxjavaDemoFragment;
import com.hotbitmapgg.studyproject.hcc.utils.ACache;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;
import com.hotbitmapgg.studyproject.hcc.utils.SnackbarUtil;
import com.hotbitmapgg.studyproject.hcc.widget.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;


public class MainActivity extends AbsBaseActivity implements View.OnClickListener
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.search_view)
    MaterialSearchView mSearchView;

    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    CircleImageView mUserAvatar;

    TextView mUserName;

    TextView mUserBio;

    private HomeFragment homeFragment;

    private ExpressionPackageFragment expressionPackageFragment;

    private RxjavaDemoFragment rxjavaDemoFragment;

    private Fragment[] fragments;

    private int currentTabIndex;

    private int index;

    private CustomWidgetFragment customWidgetFragment;

    private MdWidgetFragment mdFragment;

    private boolean isLogin = false;

    private GitHubUserInfo mUserInfo;

    private Subscription subscribe;

    private long exitTime;


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

        expressionPackageFragment = ExpressionPackageFragment.newInstance();
        homeFragment = HomeFragment.newInstance();
        rxjavaDemoFragment = RxjavaDemoFragment.newInstance();
        customWidgetFragment = CustomWidgetFragment.newInstance();
        mdFragment = MdWidgetFragment.newInstance();

        fragments = new Fragment[]{homeFragment, customWidgetFragment, rxjavaDemoFragment, expressionPackageFragment, mdFragment};

        getFragmentManager().beginTransaction().replace(R.id.content, homeFragment).commit();
        mFloatingActionButton.setVisibility(View.VISIBLE);


        subscribe = RxBus2.getInstance().toObserverable(String.class)
                .subscribe(new Action1<String>()
                {

                    @Override
                    public void call(String s)
                    {

                        if (!TextUtils.isEmpty(s))
                        {
                            LogUtil.all(s);
                            if (s.equals(ConstantUtil.CODE_SUCCESS))
                            {
                                // 登录成功
                                isLogin = true;
                                setUserInfo();
                            } else if (s.equals(ConstantUtil.CODE_LOGOUT))
                            {
                                //退出登录
                                isLogin = false;
                                clearUserInfo();
                            }
                        }
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        LogUtil.all("用户登录更新失败");
                    }
                });

        initSearchView();
    }

    private void initSearchView()
    {

        mSearchView.adjustTintAlpha(0.8f);

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query)
            {

                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {

                return false;
            }
        });


        mSearchView.setSearchViewListener(new MaterialSearchView.SearchViewListener()
        {

            @Override
            public void onSearchViewOpened()
            {

                mFloatingActionButton.setVisibility(View.GONE);
            }

            @Override
            public void onSearchViewClosed()
            {

                mFloatingActionButton.setVisibility(View.VISIBLE);
            }
        });

        mSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                TextView tv = (TextView) view.findViewById(R.id.tv_str);

                if (tv != null)
                {
                    mSearchView.setQuery(tv.getText().toString(), false);
                }
            }
        });
    }

    private void search(String query)
    {

        LogUtil.all(query);
    }

    private void clearUserInfo()
    {

        mUserAvatar.setImageResource(R.drawable.ic_slide_menu_avatar_no_login);
        mUserName.setText("立即登录");
        mUserBio.setText("使用你的GitHub账号进行登录");
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("Gank.Io");
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setDisplayHomeAsUpEnabled(true);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name);

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
            case R.id.action_debug:
                startActivity(new Intent(MainActivity.this, ApiDebugActivity.class));
                return true;
            case R.id.action_search:
                mSearchView.openSearch();
                return true;
            case R.id.action_today:
                startTodayGank();
                return true;

            case R.id.action_about:
                // 关于界面
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startTodayGank()
    {
        // 获取今日的时间
        String timeStr = SimpleDateFormat.getDateInstance().format(new Date());
        String replaceStr = timeStr.replace("年", "/").replace("月", "/").replace("日", "/");
        String[] splitStrs = replaceStr.split("/");
        int year = Integer.parseInt(splitStrs[0]);
        int month = Integer.parseInt(splitStrs[1]);
        int day = Integer.parseInt(splitStrs[2]);

        GankToDayActivity.luanhcer(MainActivity.this, year, month, day);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {

        View headerView = navigationView.getHeaderView(0);
        mUserAvatar = (CircleImageView) headerView.findViewById(R.id.github_user_avatar);
        mUserName = (TextView) headerView.findViewById(R.id.github_user_name);
        mUserBio = (TextView) headerView.findViewById(R.id.github_user_bio);
        mUserAvatar.setOnClickListener(this);

        setUserInfo();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {

                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        index = 0;
                        addFragment(fragments[0]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("Gank.IO");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.nav_messages:
                        index = 1;
                        addFragment(fragments[1]);
                        mToolbar.setTitle("CustomWidget");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_my_focus:
                        index = 2;
                        addFragment(fragments[2]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("RxJava");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_foucs_me:
                        index = 4;
                        addFragment(fragments[4]);
                        mToolbar.setTitle("MaterialDesign");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_article:
                        index = 3;
                        addFragment(fragments[3]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("AqualandFace");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, HotBitmapGGActivity.class));
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }


    public void addFragment(Fragment fragment)
    {

        FragmentTransaction trx = getFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded())
        {
            trx.add(R.id.content, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }

    @OnClick(R.id.fab)
    void startPostGank()
    {

        startActivity(new Intent(MainActivity.this, GankPostActivity.class));
    }


    @Override
    public void onClick(View v)
    {

        if (v.getId() == R.id.github_user_avatar)
        {
            if (isLogin)
            {
                startActivity(new Intent(MainActivity.this, GitHubUserInfoActivity.class));
            } else
            {
                GitHubLoginWebActivity.luancher(MainActivity.this, ConstantUtil.GITHUB_LOGIN_URL);
            }
        }
    }

    public void setUserInfo()
    {

        mUserInfo = (GitHubUserInfo) ACache.get(MainActivity.this).getAsObject(ConstantUtil.CACHE_USER_KEY);

        if (mUserInfo != null)
        {
            isLogin = true;

            LogUtil.all(mUserInfo.avatarUrl);
            Glide.with(MainActivity.this)
                    .load(mUserInfo.avatarUrl)
                    .asBitmap()
                    .placeholder(R.drawable.ic_slide_menu_avatar_no_login)
                    .into(new BitmapImageViewTarget(mUserAvatar)
                    {

                        @Override
                        protected void setResource(Bitmap resource)
                        {

                            mUserAvatar.setImageDrawable(RoundedBitmapDrawableFactory.create
                                    (MainActivity.this.getResources(), resource));
                        }
                    });

            mUserName.setText(mUserInfo.name);
            mUserBio.setText(mUserInfo.bio);
        } else
        {
            isLogin = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (mSearchView != null)
            {
                if (mSearchView.isOpen())
                {
                    mSearchView.closeSearch();
                } else
                {
                    logoutApp();
                }
            } else
            {
                logoutApp();
            }
        }

        return true;
    }

    private void logoutApp()
    {

        if (System.currentTimeMillis() - exitTime > 2000)
        {
            SnackbarUtil.showMessage(mDrawerLayout, "再按一次退出AndroidRank");
            exitTime = System.currentTimeMillis();
        } else
        {
            finish();
        }
    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();

        // 取消RxBus的订阅事件
        if (subscribe != null && !subscribe.isUnsubscribed())
        {
            subscribe.unsubscribe();
        }

        if (mSearchView != null)
        {
            if (mSearchView.isOpen())
            {
                mSearchView.closeSearch();
                mSearchView.clearAll();
            }
        }
    }
}
