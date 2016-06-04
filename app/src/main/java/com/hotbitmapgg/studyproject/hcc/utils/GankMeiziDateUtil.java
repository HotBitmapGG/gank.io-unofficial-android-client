package com.hotbitmapgg.studyproject.hcc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gank妹子的创建时间转换工具类
 */
public class GankMeiziDateUtil
{

    private final static String TIME_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    private final static String TIME_FORMAT_2 = "yy/MM/dd HH:mm:ss";


    public static String convertTime(String createdAt)
    {

        try
        {
            SimpleDateFormat inputFormat = new SimpleDateFormat(TIME_FORMAT_1);
            SimpleDateFormat outputFormat = new SimpleDateFormat(TIME_FORMAT_2);

            Date date = inputFormat.parse(createdAt);
            String time = outputFormat.format(date);

            return time;
        } catch (ParseException e)
        {
            e.printStackTrace();

            return "";
        }
    }
}
