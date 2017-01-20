package com.hotbitmapgg.gank.ui.fragment;

import com.hotbitmapgg.gank.ui.activity.WebActivity;
import com.hotbitmapgg.gank.utils.SnackbarUtil;
import com.hotbitmapgg.studyproject.R;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by hcc on 16/7/9 21:59
 * 100332338@qq.com
 */
public class AboutFragment extends PreferenceFragment
    implements Preference.OnPreferenceClickListener {

  public static AboutFragment newInstance() {

    return new AboutFragment();
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preference_about);
    Preference author = findPreference("author");
    Preference gank = findPreference("gank");
    Preference other = findPreference("other");

    author.setOnPreferenceClickListener(this);
    gank.setOnPreferenceClickListener(this);
    other.setOnPreferenceClickListener(this);
  }


  @Override
  public boolean onPreferenceClick(Preference preference) {

    if (preference.getKey().equals("author")) {
      WebActivity.launch(getActivity(), "https://github.com/HotBitmapGG", "HotBitmapGG");
    } else if (preference.getKey().equals("gank")) {
      WebActivity.launch(getActivity(), "http://gank.io/", "gank.io");
    } else {
      SnackbarUtil.showMessage(getView(), "请点击作者直达我的GitHub查看更多开源项目哦~");
    }
    return false;
  }
}
