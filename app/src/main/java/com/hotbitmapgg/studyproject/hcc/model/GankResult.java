package com.hotbitmapgg.studyproject.hcc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GankResult
{
    public boolean error;

    @SerializedName("results")
    public List<GankBeautyBean> beautys;


    public class GankBeautyBean
    {
        public String createdAt;

        public String url;
    }
}
