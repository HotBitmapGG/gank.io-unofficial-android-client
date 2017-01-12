package com.hotbitmapgg.gank.network;

import com.hotbitmapgg.gank.model.GitHubUserInfo;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Github授权登录流程调用
 * <p/>
 * https://github.com/login?return_to=%2Flogin%2Foauth%2Fauthorize%3Fclient_id%3D3f0b872a51a4b0c45065%26redirect_uri%3Dhttp%253A%252F%252Fexample
 * <p/>
 * https://github.com/login/oauth/authorize?client_id=3f0b872a51a4b0c45065&state=AndroidRank&redirect_uri=http://example.com/path
 * <p/>
 * http://example.com/path?code=0daf784b105d00ec84d9&state=AndroidRank
 * <p/>
 * https://github.com/login/oauth/access_token?client_id=3f0b872a51a4b0c45065&client_secret=e888c5cdce46cc192bd505e603c8b57b8bf3987d&code=2e79c3997013909e58ce&redirect_uri=http://example.com/path
 * <p/>
 * access_token=55a08af0e6b68800fb0fb7202d0ef16a3ca67754&scope=&token_type=bearer
 * <p/>
 * https://api.github.com/user?access_token=55a08af0e6b68800fb0fb7202d0ef16a3ca67754
 * <p/>
 * https://api.github.com/users/HotBitmapGG/followers
 * <p/>
 * https://api.github.com/users/HotBitmapGG/following
 * <p/>
 * https://api.github.com/users/HotBitmapGG/starred
 */
public interface GithubApi {

  /**
   * 三方登录授权获取Token
   */
  @GET("access_token")
  Observable<ResponseBody> getLoginToken(@Query("client_id") String id,
                                         @Query("client_secret") String secret,
                                         @Query("code") String code,
                                         @Query("redirect_uri") String uri);

  /**
   * 根据Token获取用户登录信息
   */
  @GET("user")
  Observable<GitHubUserInfo> getGithubLoginUserInfo(@Query("access_token") String token);

  /**
   * 关注用户的人
   */
  @GET("users/{userName}/followers")
  Observable<ResponseBody> getGitHubFollowers(@Path("userName") String name);

  /**
   * 用户关注的人
   */
  @GET("users/{userName}/following")
  Observable<ResponseBody> getGitHubFolloweing(@Path("userName") String name);

  /**
   * 获取用户最近的star项目信息
   */
  @GET("users/{userName}/starred")
  Observable<ResponseBody> getGitHubStarred(@Path("userName") String name);
}
