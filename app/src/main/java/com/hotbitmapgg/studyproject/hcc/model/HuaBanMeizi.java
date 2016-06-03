package com.hotbitmapgg.studyproject.hcc.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hcc on 16/5/30 20:48
 * 100332338@qq.com
 */
public class HuaBanMeizi
{

    @SerializedName("showapi_res_code")
    public int code;

    @SerializedName("showapi_res_error")
    public String error;

    @SerializedName("showapi_res_body")
    public JsonObject list;

    public List<HuaBanMeiziInfo> infos;


    /**
     * 解析json返回的数据 拼接为集合
     *
     * @param json
     * @return
     */
    public static HuaBanMeizi createFromJson(String json)
    {

        HuaBanMeizi result = new Gson().fromJson(json, HuaBanMeizi.class);
        Iterator<Map.Entry<String,JsonElement>> iterator = result.list.entrySet().iterator();
        if (result.infos == null)
        {
            result.infos = new ArrayList<>();
        }
        while (iterator.hasNext())
        {
            Map.Entry<String,JsonElement> element = iterator.next();
            try
            {
                result.infos.add(new Gson().fromJson(element.getValue(), HuaBanMeiziInfo.class));
            } catch (Exception e)
            {

            }
        }

        result.list = null;

        return result;
    }
}
