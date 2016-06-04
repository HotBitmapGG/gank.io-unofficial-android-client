package com.hotbitmapgg.studyproject.hcc.widget.tablayout;


public class YZBRate
{
    private String date;
    private double rate;

    public YZBRate(String date, double rate)
    {
        this.date = date;
        this.rate = rate;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }
}
