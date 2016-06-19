package com.hotbitmapgg.studyproject.hcc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubQueryUserInfo
{

    @SerializedName("total_count")
    public int totalCount;

    @SerializedName("incomplete_results")
    public boolean isIncompleteResults;

    public List<UserInfo> items;


    public class UserInfo
    {

        @SerializedName("login")
        public String userName;

        @SerializedName("avatar_url")
        public String avatarUrl;

        @SerializedName("url")
        public String Userurl;

        @SerializedName("html_url")
        public String githubUrl;
    }
}
