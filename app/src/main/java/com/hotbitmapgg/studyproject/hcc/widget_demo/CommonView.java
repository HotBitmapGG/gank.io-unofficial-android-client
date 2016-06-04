package com.hotbitmapgg.studyproject.hcc.widget_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义控件练习
 * <p/>
 * View的绘制流程练习代码
 */
public class CommonView extends View
{

    private Paint mPaint;

    public CommonView(Context context)
    {
        this(context, null);
    }

    public CommonView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init()
    {
        mPaint = new Paint();
    }


    /**
     * View的测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * View的布局
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * View的绘制
     * <p/>
     * View的绘制大致分为4步
     * 1，绘制背景
     * 2，绘制自己
     * 3，绘制子View
     * 4，绘制滚动条
     *
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas)
    {

        /**
         * Canvas基本Api使用
         * 绘制各种图形
         */


//        //绘制白色矩形
//        mPaint.setColor(Color.LTGRAY);
//        canvas.drawRect(100, 100, 800, 800, mPaint);
//        mPaint.reset();

//        //绘制直线
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStrokeWidth(10);
//        canvas.drawLine(0, 100, 500, 0, mPaint);
//        mPaint.reset();
//
//
//        //绘制带边框的矩形
//        mPaint.setColor(Color.BLACK);
//        mPaint.setARGB(150, 90, 255, 0);
//        mPaint.setStyle(Paint.Style.STROKE);
//        RectF rect = new RectF(0 , 0 , 100 , 100);
//        canvas.drawRect(rect, mPaint);
//        mPaint.reset();
//
//
//        //绘制实心圆
//        mPaint.setColor(Color.BLUE);
//        mPaint.setStrokeWidth(14);
//        mPaint.setAntiAlias(true);
//        canvas.drawCircle(200, 200, 100, mPaint);
//        mPaint.reset();
//
//        //绘制椭圆
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setAntiAlias(true);
//        mPaint.setStrokeWidth(10);
//        RectF rectF = new RectF(200, 400, 600, 600);
//        canvas.drawOval(rectF, mPaint);
//        mPaint.reset();

//        //绘制文字
//        mPaint.setColor(Color.BLACK);
//        mPaint.setTextSize(60);
//        mPaint.setUnderlineText(true);
//        canvas.drawText("绘制文字", 100, 100, mPaint);
//        mPaint.reset();


        /**
         * canvas.translate的使用
         *
         * 平移画布
         */

//        canvas.drawColor(Color.LTGRAY);
//        mPaint.setColor(Color.BLUE);
//        mPaint.setTextSize(60);
//        canvas.drawText("平移前的文字", 200, 200, mPaint);
//        mPaint.reset();
//        canvas.translate(200, 200);
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setTextSize(60);
//        canvas.drawText("平移后的文字", 200, 200, mPaint);


        /**
         * canvas.rotate
         *
         * 旋转画布
         */

//        canvas.drawColor(Color.LTGRAY);
//        mPaint.setColor(Color.BLUE);
//        mPaint.setTextSize(60);
//        canvas.drawText("平移前的文字", 200, 200, mPaint);
//        mPaint.reset();
//        canvas.rotate(10);
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setTextSize(60);
//        canvas.drawText("平移后的文字", 200, 200, mPaint);


        /**
         * canvas.clipRect
         *
         * 画布裁剪
         */

//        canvas.drawColor(Color.GREEN);
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setTextSize(60);
//        canvas.drawText("裁剪前的内容", 200, 200, mPaint);
//        Rect rect = new Rect(200, 400, 800, 800);
//        canvas.clipRect(rect);
//        canvas.drawColor(Color.BLUE);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawText("裁剪后的内容", 200, 200, mPaint);


        /**
         * canvas.save和canvas.restore
         *
         * 保存和重置
         */


//        canvas.drawColor(Color.GREEN);
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setTextSize(60);
//        canvas.drawText("裁剪前的内容", 200, 200, mPaint);
//        canvas.save();
//        Rect rect = new Rect(100, 100, 400, 400);
//        canvas.clipRect(rect);
//        canvas.drawColor(Color.BLUE);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawText("裁剪后的内容", 200, 200, mPaint);
//        canvas.restore();
//        canvas.drawText("1111", 100, 200, mPaint);


        /**
         * Shader 渲染
         *
         * BitmapShader———图像渲染
         * LinearGradient——–线性渲染
         * RadialGradient——–环形渲染
         * SweepGradient——–扫描渲染
         * ComposeShader——组合渲染
         */

        /**
         * TileMode知识点
         *
         * REPEAT ：重复
         * MIRROR ：镜像
         * CLAMP：拉伸
         */

//        //使用BitmapShader实现圆形图片
//        mPaint.setAntiAlias(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.no_media);
//        //创建BitmapShader
//        int angle = bitmap.getWidth() / 2;
//        BitmapShader bitmapShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//        mPaint.setShader(bitmapShader);
//        canvas.translate(200, 200);
//        canvas.drawCircle(angle, angle, angle, mPaint);


        /**
         * PathEffect  Path路径相关知识
         *
         * CornerPathEffect用平滑的方式衔接Path的各部分
         * DashPathEffect将Path的线段虚线化
         * PathDashPathEffect与DashPathEffect效果类似但需要自定义路径虚线的样式
         * DiscretePathEffect离散路径效果
         * ComposePathEffect两种样式的组合。先使用第一种效果然后在此基础上应用第二种效果
         * SumPathEffect两种样式的叠加。先将两种路径效果叠加起来再作用于Path
         */

//        canvas.translate(0, 300);
//        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(8);
//        mPaint.setColor(Color.YELLOW);
//        Path path = new Path();
//        path.moveTo(0, 40);
//        for (int i = 0; i < 40; i++)
//        {
//            path.lineTo(i * 30, (float) (Math.random() * 150));
//        }
//        canvas.drawPath(path, mPaint);
//        canvas.translate(0, 400);
//
//        //圆滑效果 不会那么尖锐
//        mPaint.setPathEffect(new CornerPathEffect(60));
//        canvas.drawPath(path, mPaint);
//        canvas.translate(0, 500);
//
//        //虚线效果
//        mPaint.setPathEffect(new DashPathEffect(new float[]{10, 19}, 1));
//        canvas.drawPath(path, mPaint);






    }

}
