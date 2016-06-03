package com.hotbitmapgg.studyproject.hcc.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotbitmapgg.studyproject.hcc.StudyApp;
import com.hotbitmapgg.studyproject.hcc.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class DataBaseOpenHelper
{
    public static final String DATA_NAME = "data.db";

    public static DataBaseOpenHelper mOpenHelper;

    //本地文件保存路径
    private File mDir = new File(StudyApp.getContext().getFilesDir(), DATA_NAME);

    Gson mGson = new Gson();

    public static DataBaseOpenHelper getInstance()
    {
        if (mOpenHelper == null)
        {
            synchronized (DataBaseOpenHelper.class)
            {
                if (mOpenHelper == null)
                {
                    mOpenHelper = new DataBaseOpenHelper();
                }

            }
        }

        return mOpenHelper;
    }

    /**
     * 读取缓存数据
     * @return
     */
    public List<Item> readItems()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        try
        {
            Reader mReader = new FileReader(mDir);
            return mGson.fromJson(mReader, new TypeToken<List<Item>>()
            {
            }.getType());
        } catch (FileNotFoundException e)
        {

            return null;
        }

    }


    /**
     * 保存缓存数据
     * @param items
     */
    public void writeItems(List<Item> items)
    {
        String json = mGson.toJson(items);
        if (!mDir.exists())
        {
            try
            {
                mDir.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            Writer mWriter = new FileWriter(mDir);
            mWriter.write(json);
            mWriter.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
