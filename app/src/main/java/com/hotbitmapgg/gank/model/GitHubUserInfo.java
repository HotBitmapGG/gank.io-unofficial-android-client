package com.hotbitmapgg.gank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Github授权登录用户信息
 * <p/>
 * "avatar_url" = "https://avatars.githubusercontent.com/u/12438779?v=3";
 * bio = "The invisible force, the most lethal";
 * blog = "http://weibo.com/u/3223089177";
 * company = "Wuhan day education technology co., LTD";
 * "created_at" = "2015-05-14T02:54:47Z";
 * email = "hotbitmapgg@gmail.com";
 * "events_url" = "https://api.github.com/users/HotBitmapGG/events{/privacy}";
 * followers = 27;
 * "followers_url" = "https://api.github.com/users/HotBitmapGG/followers";
 * following = 151;
 * "following_url" = "https://api.github.com/users/HotBitmapGG/following{/other_user}";
 * "gists_url" = "https://api.github.com/users/HotBitmapGG/gists{/gist_id}";
 * "gravatar_id" = "";
 * hireable = 1;
 * "html_url" = "https://github.com/HotBitmapGG";
 * id = 12438779;
 * location = "Wuhan China";
 * login = HotBitmapGG;
 * name = HotBitmapGG;
 * "organizations_url" = "https://api.github.com/users/HotBitmapGG/orgs";
 * "public_gists" = 0;
 * "public_repos" = 142;
 * "received_events_url" = "https://api.github.com/users/HotBitmapGG/received_events";
 * "repos_url" = "https://api.github.com/users/HotBitmapGG/repos";
 * "site_admin" = 0;
 * "starred_url" = "https://api.github.com/users/HotBitmapGG/starred{/owner}{/repo}";
 * "subscriptions_url" = "https://api.github.com/users/HotBitmapGG/subscriptions";
 * type = User;
 * "updated_at" = "2016-06-17T00:46:40Z";
 * url = "https://api.github.com/users/HotBitmapGG";
 */
public class GitHubUserInfo implements Serializable {

  //头像地址
  @SerializedName("avatar_url")
  public String avatarUrl;

  // 个人简介
  public String bio;

  // 个人博客连接
  public String blog;

  //所在公司名称
  public String company;

  // 注册时间
  @SerializedName("created_at")
  public String createdTime;

  // 邮箱
  public String email;

  // 关注用户的数量
  public int followers;

  //用户关注的数量
  public int following;

  //Github地址
  @SerializedName("html_url")
  public String htmlUrl;

  //所在地
  public String location;

  // 昵称
  public String name;

  //登录用户名
  public String login;

  //最近登录时间
  @SerializedName("updated_at")
  public String updatedTime;

  //用户id
  public long id;
}
