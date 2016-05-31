package com.hotbitmapgg.studyproject.hcc.widget_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;

import butterknife.Bind;


public class BitmapStudyActivity extends AbsBaseActivity
{

    @Bind(R.id.image_view)
    ImageView imageView;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_bitmap_study;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {
        //drawBitmap();
        //setRoundImageView(R.mipmap.no_media, 20, imageView);

//        Canvas canvas = new Canvas();
//        drawMatrixBitmap(R.mipmap.no_media , canvas);
    }

    @Override
    public void initToolBar()
    {

    }

    /**
     * 绘制Bitmap
     */
    public void drawBitmap()
    {
        //创建一个bitmap
        Bitmap bitmap = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
        //创建Canvas画布
        Canvas canvas = new Canvas(bitmap);
        //设置画布背景颜色
        canvas.drawColor(Color.LTGRAY);
        //创建画笔
        Paint paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(Color.WHITE);
        //设置字体大小
        paint.setTextSize(60);
        //绘制文字
        canvas.drawText("WhatTheFuck!", 200, 200, paint);
        //将绘制好的显示在ImageView上
        imageView.setImageBitmap(bitmap);

    }


    /**
     * 创建圆角图片
     * <p/>
     * PorterDuffXfermode的使用
     * <p/>
     * 相关知识 方便复习:
     * <p/>
     * PorterDuff.Mode.CLEAR
     * 绘制不会提交到画布上
     * PorterDuff.Mode.SRC
     * 只显示绘制源图像
     * PorterDuff.Mode.DST
     * 只显示目标图像，即已在画布上的初始图像
     * PorterDuff.Mode.SRC_OVER
     * 正常绘制显示，即后绘制的叠加在原来绘制的图上
     * PorterDuff.Mode.DST_OVER
     * 上下两层都显示但是下层(DST)居上显示
     * PorterDuff.Mode.SRC_IN
     * 取两层绘制的交集且只显示上层(SRC)
     * PorterDuff.Mode.DST_IN
     * 取两层绘制的交集且只显示下层(DST)
     * PorterDuff.Mode.SRC_OUT
     * 取两层绘制的不相交的部分且只显示上层(SRC)
     * PorterDuff.Mode.DST_OUT
     * 取两层绘制的不相交的部分且只显示下层(DST)
     * PorterDuff.Mode.SRC_ATOP
     * 两层相交，取下层(DST)的非相交部分和上层(SRC)的相交部分
     * PorterDuff.Mode.DST_ATOP
     * 两层相交，取上层(SRC)的非相交部分和下层(DST)的相交部分
     * PorterDuff.Mode.XOR
     * 挖去两图层相交的部分
     * PorterDuff.Mode.DARKEN
     * 显示两图层全部区域且加深交集部分的颜色
     * PorterDuff.Mode.LIGHTEN
     * 显示两图层全部区域且点亮交集部分的颜色
     * PorterDuff.Mode.MULTIPLY
     * 显示两图层相交部分且加深该部分的颜色
     * PorterDuff.Mode.SCREEN
     * 显示两图层全部区域且将该部分颜色变为透明色
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    public Bitmap createRoundBitmap(Bitmap bitmap, float pixels)
    {

        //先获取Bitmap的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //创建一个新的Bitmap
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //创建画布
        Canvas canvas = new Canvas(newBitmap);
        //创建画笔
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(Color.BLACK);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //创建矩形
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        //绘制圆角矩形
        canvas.drawRoundRect(rectF, pixels, pixels, paint);
        //创建 PorterDuffXfermode
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        //给画笔设置画图模式
        paint.setXfermode(xfermode);
        //绘制到Bitmap上
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return newBitmap;
    }


    /**
     * 设置图片给ImageView显示圆角
     *
     * @param res
     * @param angle
     * @param imageView
     */
    public void setRoundImageView(int res, float angle, ImageView imageView)
    {
//        Drawable drawable = getResources().getDrawable(res);
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//        Bitmap bitmap = bitmapDrawable.getBitmap();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);

        Bitmap roundBitmap = createRoundBitmap(bitmap, angle);
        imageView.setImageBitmap(roundBitmap);
    }


    /**
     * 利用matrix来实现图片的平移和缩放
     */
    public void drawMatrixBitmap(int res , Canvas canvas)
    {
        //创建画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //创建bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //创建Matrix矩阵
        Matrix matrix = new Matrix();
        //绘制bitmap
        canvas.drawBitmap(bitmap, matrix, paint);
        //平移
        matrix.setTranslate(width / 2, height);
        canvas.drawBitmap(bitmap, matrix, paint);
        //缩放
        matrix.postScale(02.f, 02.f);
        canvas.drawBitmap(bitmap, matrix, paint);


    }

}
