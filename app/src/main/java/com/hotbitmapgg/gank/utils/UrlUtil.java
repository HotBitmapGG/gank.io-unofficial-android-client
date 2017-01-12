package com.hotbitmapgg.gank.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取url连接中的参数值
 */
public class UrlUtil {

  /**
   * 解析出url请求的路径，包括页面
   *
   * @param strURL url地址
   * @return url路径
   */
  public static String UrlPage(String strURL) {

    String strPage = null;
    String[] arrSplit = null;

    strURL = strURL.trim().toLowerCase();

    arrSplit = strURL.split("[?]");
    if (strURL.length() > 0) {
      if (arrSplit.length > 1) {
        if (arrSplit[0] != null) {
          strPage = arrSplit[0];
        }
      }
    }

    return strPage;
  }


  /**
   * 去掉url中的路径，留下请求参数部分
   *
   * @param strURL url地址
   * @return url请求参数部分
   */
  private static String TruncateUrlPage(String strURL) {

    String strAllParam = null;
    String[] arrSplit = null;

    strURL = strURL.trim().toLowerCase();

    arrSplit = strURL.split("[?]");
    if (strURL.length() > 1) {
      if (arrSplit.length > 1) {
        if (arrSplit[1] != null) {
          strAllParam = arrSplit[1];
        }
      }
    }

    return strAllParam;
  }


  /**
   * 解析出url参数中的键值对
   * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
   *
   * @param URL url地址
   * @return url请求参数部分
   */
  public static Map<String, String> URLRequest(String URL) {

    Map<String, String> mapRequest = new HashMap<String, String>();

    String[] arrSplit = null;

    String strUrlParam = TruncateUrlPage(URL);
    if (strUrlParam == null) {
      return mapRequest;
    }
    //每个键值为一组
    arrSplit = strUrlParam.split("[&]");
    for (String strSplit : arrSplit) {
      String[] arrSplitEqual = null;
      arrSplitEqual = strSplit.split("[=]");

      //解析出键值
      if (arrSplitEqual.length > 1) {
        //正确解析
        mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
      } else {
        if (arrSplitEqual[0] != "") {
          //只有参数没有值，不加入
          mapRequest.put(arrSplitEqual[0], "");
        }
      }
    }
    return mapRequest;
  }


  /**
   * 获取参数中token值
   */
  public static String getTokenByUrl(String params) {

    String[] tokenParams = params.split("&");
    String[] token = tokenParams[0].split("=");
    return token[1];
  }
}
