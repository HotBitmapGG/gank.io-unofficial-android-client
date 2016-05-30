package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.base.ConstantUtil;
import com.hotbitmapgg.studyproject.hcc.model.HuaBanMeizi;
import com.hotbitmapgg.studyproject.hcc.network.RetrofitHelper;
import com.hotbitmapgg.studyproject.hcc.utils.LogUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HuaBanMeiziActivity extends AbsBaseActivity
{

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_huaban_meizi;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        RetrofitHelper.getHuaBanMeiziApi()
                .getHuaBanMeizi("10","1", ConstantUtil.APP_ID,"34",ConstantUtil.APP_SIGN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>()
                {

                    @Override
                    public void call(ResponseBody responseBody)
                    {

                        try
                        {
                            String response = responseBody.string();
                            if(!TextUtils.isEmpty(response))
                            {
                                HuaBanMeizi meizi = HuaBanMeizi.createFromJson(response);
                                if (meizi != null)
                                {
                                    List<HuaBanMeizi.MeiziInfo> infos = meizi.infos;
                                }
                            }
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {
                        LogUtil.all(throwable.getMessage());
                    }
                });
    }

    @Override
    public void initToolBar()
    {

    }
}
