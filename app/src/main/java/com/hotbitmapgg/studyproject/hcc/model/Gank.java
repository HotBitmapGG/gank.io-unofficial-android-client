package com.hotbitmapgg.studyproject.hcc.model;

import java.util.List;

public class Gank
{

    public boolean error;

    public List<AndroidInfo> results;


    public class AndroidInfo
    {
        public String _id;

        public String createdAt;

        public String desc;

        public String publishedAt;

        public String source;

        public String type;

        public String url;

        public boolean used;

        public String who;

    }
}
