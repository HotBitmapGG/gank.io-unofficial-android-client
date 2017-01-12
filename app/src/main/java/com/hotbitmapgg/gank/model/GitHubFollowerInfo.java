package com.hotbitmapgg.gank.model;

import com.google.gson.annotations.SerializedName;

/**
 * "login": "yestin0303",
 * "id": 12086699,
 * "avatar_url": "https://avatars.githubusercontent.com/u/12086699?v=3",
 * "gravatar_id": "",
 * "url": "https://api.github.com/users/yestin0303",
 * "html_url": "https://github.com/yestin0303",
 * "followers_url": "https://api.github.com/users/yestin0303/followers",
 * "following_url": "https://api.github.com/users/yestin0303/following{/other_user}",
 * "gists_url": "https://api.github.com/users/yestin0303/gists{/gist_id}",
 * "starred_url": "https://api.github.com/users/yestin0303/starred{/owner}{/repo}",
 * "subscriptions_url": "https://api.github.com/users/yestin0303/subscriptions",
 * "organizations_url": "https://api.github.com/users/yestin0303/orgs",
 * "repos_url": "https://api.github.com/users/yestin0303/repos",
 * "events_url": "https://api.github.com/users/yestin0303/events{/privacy}",
 * "received_events_url": "https://api.github.com/users/yestin0303/received_events",
 * "type": "User",
 * "site_admin": false
 */
public class GitHubFollowerInfo {

  public String login;

  public long id;

  @SerializedName("avatar_url")
  public String avatarUrl;

  @SerializedName("html_url")
  public String htlmUrl;
}
