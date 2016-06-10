package com.hotbitmapgg.studyproject.hcc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GankMeiziResult
{

    public boolean error;

    @SerializedName("results")
    public List<GankMeiziInfo> gankMeizis;
}
