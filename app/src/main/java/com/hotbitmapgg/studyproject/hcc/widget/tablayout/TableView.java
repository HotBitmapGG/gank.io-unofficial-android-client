package com.hotbitmapgg.studyproject.hcc.widget.tablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class TableView extends View
{
    private static final int MAX_ITEM = 10;

    private String titleText1 = "测试";

    private String titleText2 = "测试";

    private int titleText1Color = Color.GREEN;

    private int titleText2Color = Color.BLUE;

    private float titleText1Size = 18;

    private float titleText2Size = 18;

    private int labelTextColor = Color.RED;

    private float labelTextSize = 12;

    private int backgroundLineColor = Color.RED;

    private float backgroundLineWidth = 2;

    private int fillColor = Color.RED;

    private int lineColor = Color.RED;

    private float lineWidth = 4;

    private int lineHCount = 6;

    private float circleRadius = 10;

    private float circleWidth = 4;

    private int circleColor = Color.WHITE;

    private int maxItem = MAX_ITEM;

    private TextPaint mTextPaint;

    private float mTitleText1Width, mTitleText2Width, mLabelLeftWidth, mLabelBottomWidth;

    private float mTitleText1Height, mTitleText2Height, mLabelTextHeight;

    private RectF rectFText = new RectF();

    private Paint.FontMetrics fontMetrics;

    private int itemWidth = -1, itemHeight = -1;

    private double maxRate = -1, minRate = -1;

    private Paint paint;

    private Path linePath = new Path();

    private ArrayList<YZBRate> items;

    public TableView(Context context)
    {
        super(context);
        init(null, 0);
    }

    public TableView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0);
    }

    public TableView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle)
    {
        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(
//                attrs, R.styleable.TableView, defStyle, 0);
//
//        titleText1 = a.getString(R.styleable.TableView_tableTitleText1);
//        titleText2 = a.getString(R.styleable.TableView_tableTitleText2);
//        titleText1Color = a.getColor(R.styleable.TableView_tableTitleText1Color, Color.RED);
//        titleText2Color = a.getColor(R.styleable.TableView_tableTitleText2Color, Color.RED);
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        titleText1Size = a.getDimension(R.styleable.TableView_tableTitleText1Size, 18);
//        titleText2Size = a.getDimension(R.styleable.TableView_tableTitleText2Size, 18);
//        labelTextColor = a.getColor(R.styleable.TableView_tableLabelTextColor, Color.RED);
//        labelTextSize = a.getDimension(R.styleable.TableView_tableLabelTextSize, 12);
//        backgroundLineColor = a.getColor(R.styleable.TableView_tableBackgroundLineColor, Color.RED);
//        backgroundLineWidth = a.getDimension(R.styleable.TableView_tableBackgroundLineWidth, 2);
//        fillColor = a.getColor(R.styleable.TableView_tableFillColor, Color.RED);
//        lineColor = a.getColor(R.styleable.TableView_tableLineColor, Color.RED);
//        lineWidth = a.getDimension(R.styleable.TableView_tableLineWidth, 4);
//        lineHCount = a.getInteger(R.styleable.TableView_tableLineHCount, 6);
//        circleWidth = a.getDimension(R.styleable.TableView_tableCircleWidth, 4);
//        circleRadius = a.getDimension(R.styleable.TableView_tableCircleRadius, 10);
//        circleColor = a.getColor(R.styleable.TableView_tableCircleFillColor, Color.WHITE);
//        maxItem = a.getInteger(R.styleable.TableView_tableMaxItem, MAX_ITEM);

        // a.recycle();

        // 初始化文本画笔
        mTextPaint = new TextPaint();
        //设置抗锯齿
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        //设置文字显示位置
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // 测量文本的宽高
        invalidateTextPaintAndMeasurements();

        //初始化划拨
        paint = new Paint();
        paint.setAntiAlias(true);
        initItem();
    }

    /**
     * 测量文本的宽高
     */
    private void invalidateTextPaintAndMeasurements()
    {
        //设置文本大小
        mTextPaint.setTextSize(titleText1Size);
        //测量文本获取宽度
        mTitleText1Width = mTextPaint.measureText(titleText1);
        //获取文本的高度
        fontMetrics = mTextPaint.getFontMetrics();
        mTitleText1Height = fontMetrics.bottom;

        mTextPaint.setTextSize(titleText2Size);
        mTitleText2Width = mTextPaint.measureText(titleText2);
        fontMetrics = mTextPaint.getFontMetrics();
        mTitleText2Height = fontMetrics.bottom;

        //设置文本颜色
        mTextPaint.setColor(titleText1Color);
    }

    /**
     * 初始化底部的日期的item
     */
    private void initItem()
    {
        ArrayList<YZBRate> items = new ArrayList<>();
        items.add(new YZBRate("11-05", 3.16));
        items.add(new YZBRate("11-06", 3.19));
        items.add(new YZBRate("11-07", 4.19));
        items.add(new YZBRate("11-08", 3.39));
        items.add(new YZBRate("11-09", 3.10));
        items.add(new YZBRate("11-10", 4.10));
        items.add(new YZBRate("11-11", 3.49));
        setItems(items);
    }

    public void setItems(ArrayList<YZBRate> items)
    {
        // 只取最后MAX_ITEM位
        while (items.size() > maxItem)
        {
            items.remove(0);
        }

        this.items = items;
        for (YZBRate item : items)
        {
            // 只保留日期，不保留月份
            String text = item.getDate();
            if (text.length() > 5)
            {
                text = text.substring(text.length() - 5);
                item.setDate(text);
            }

            // 获取最大值和最小值
            if (maxRate == -1)
            {
                maxRate = item.getRate();
            }
            else
            {
                maxRate = Math.max(item.getRate(), maxRate);
            }

            if (minRate == -1)
            {
                minRate = item.getRate();
            }
            else
            {
                minRate = Math.min(item.getRate(), minRate);
            }
        }
        if (maxRate - minRate <= 0.5)
        {
            maxRate += 0.2;
            minRate -= 0.2;
            if (minRate < 0)
            {
                minRate = 0;
            }
        }
        itemWidth = -1;
        itemHeight = -1;

        if (items.size() > 0)
        {
            String rate = String.valueOf(items.get(0).getRate());
            mTextPaint.setTextSize(labelTextSize);
            mLabelLeftWidth = mTextPaint.measureText(rate);
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mLabelTextHeight = fontMetrics.bottom;
            mLabelBottomWidth = mTextPaint.measureText(items.get(0).getDate());
        }

        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec)
    {

        return MeasureSpec.getSize(measureSpec);
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec)
    {

        return MeasureSpec.getSize(measureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        drawTitle(canvas);
        drawBackgroundLine(canvas);
        drawLabel(canvas);
        drawLine(canvas);
    }

    private void drawTitle(Canvas canvas)
    {
        //绘制Title
        rectFText.set(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + mTitleText1Width,
                getPaddingTop() + mTitleText1Height);
        float baseline = rectFText.top + (rectFText.bottom - rectFText.top
                - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;

        // title1
        mTextPaint.setColor(titleText1Color);
        mTextPaint.setTextSize(titleText1Size);
        canvas.drawText(titleText1,
                getPaddingLeft(),
                baseline,
                mTextPaint);
        // title2
        mTextPaint.setColor(titleText2Color);
        mTextPaint.setTextSize(titleText2Size);
        canvas.drawText(titleText2,
                (float) (getPaddingLeft() * 1.5 + mTitleText1Width),
                baseline,
                mTextPaint);
    }

    private void drawBackgroundLine(Canvas canvas)
    {
        // 起始坐标
        int startX = (int) (getPaddingLeft() + mLabelLeftWidth + getPaddingLeft() / 2);
        int startY = (int) ((getPaddingTop() * 2 + mTitleText1Height));
        int count = 7;
        if (items != null && items.size() > 0)
        {
            count = items.size();
        }
        if (itemWidth <= 0)
        {
            itemWidth = (int) ((getWidth() - getPaddingLeft() * 1.5 - mLabelLeftWidth
                    - getPaddingRight() - circleRadius) / (count - 1));
        }
        if (itemHeight <= 0)
        {
            itemHeight = (int) ((getHeight() - getPaddingTop() * 2 - mTitleText1Height -
                    getPaddingBottom() * 1.5 - mLabelTextHeight) / (lineHCount - 0.5));
        }
        int endY = (int) (startY + itemHeight * (lineHCount - 0.5));
        // 画竖线
        paint.setColor(backgroundLineColor);
        paint.setStrokeWidth(backgroundLineWidth);
        for (int i = 0; i < count; i++)
        {
            canvas.drawLine(startX + i * itemWidth, startY, startX + i * itemWidth, endY, paint);
        }
        // 画横线
        startY += itemHeight / 2;
        int endX = startX + itemWidth * (count - 1);
        for (int i = 0; i < lineHCount; i++)
        {
            canvas.drawLine(startX, startY + i * itemHeight, endX, startY + i * itemHeight, paint);
        }
    }

    private void drawLabel(Canvas canvas)
    {
        if (items == null || items.size() <= 0)
        {
            return;
        }
        // rate
        double pre = (maxRate - minRate) / (lineHCount - 2);
        int startX = getPaddingLeft();
        int startY = (int) (getPaddingTop() * 2 + mTitleText1Height +
                itemHeight / 2 - mLabelTextHeight / 2);
        rectFText.set(startX, startY, startX + mLabelLeftWidth,
                startY + mLabelTextHeight);
        float baseline = rectFText.top + (rectFText.bottom - rectFText.top
                - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;

        mTextPaint.setTextSize(labelTextSize);
        mTextPaint.setColor(labelTextColor);
        for (int i = 0; i < lineHCount - 1; i++)
        {
            canvas.drawText(format(maxRate - pre * i),
                    startX,
                    baseline + itemHeight * i,
                    mTextPaint);
        }
        // date
        startX = (int) (getPaddingLeft() * 1.5 + mLabelLeftWidth - mLabelBottomWidth / 2);
        startY = (int) (getHeight() - getPaddingBottom() - mLabelTextHeight);
        rectFText.set(startX, startY, startX + mLabelBottomWidth,
                startY + mLabelTextHeight);
        baseline = rectFText.top + (rectFText.bottom - rectFText.top
                - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        for (int i = 0, size = items.size(); i < size; i++)
        {
            canvas.drawText(items.get(i).getDate(),
                    startX + itemWidth * i,
                    baseline,
                    mTextPaint);
        }
    }

    private String format(double v)
    {
        String path = String.valueOf(v);
        return path;
    }

    private void drawLine(Canvas canvas)
    {
        if (items == null || items.size() <= 0)
        {
            return;
        }
        //Path路径
        linePath.reset();
        // 画线
        int startX = (int) (getPaddingLeft() * 1.5 + mLabelLeftWidth);
        int startY = (int) (getPaddingTop() * 2 + mTitleText1Height + itemHeight / 2);
        double rate = items.get(0).getRate();
        float firstRateY = (float) (startY + (maxRate - rate) / (maxRate - minRate)
                * itemHeight * (lineHCount - 2));
        float rateY = firstRateY;
        linePath.moveTo(startX, rateY);
        for (int i = 0, size = items.size(); i < size; i++)
        {
            rate = items.get(i).getRate();
            rateY = (float) (startY + (maxRate - rate) / (maxRate - minRate) * itemHeight
                    * (lineHCount - 2));
            linePath.lineTo(startX + itemWidth * i, rateY);
        }
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(linePath, paint);

        // 填充颜色
        linePath.lineTo(startX + itemWidth * (items.size() - 1),
                (float) (startY + itemHeight * (lineHCount - 1)));
        linePath.lineTo(startX, (float) (startY + itemHeight * (lineHCount - 1)));
        linePath.lineTo(startX, firstRateY);
        paint.setColor(fillColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(linePath, paint);

        // 最后一个小圆圈
        paint.setColor(lineColor);
        canvas.drawCircle(startX + itemWidth * (items.size() - 1), rateY,
                circleRadius + circleWidth, paint);
        paint.setColor(circleColor);
        canvas.drawCircle(startX + itemWidth * (items.size() - 1), rateY,
                circleRadius, paint);

    }

    public String getTitleText1()
    {
        return titleText1;
    }

    public void setTitleText1(String titleText1)
    {
        this.titleText1 = titleText1;
        invalidate();
    }

    public String getTitleText2()
    {
        return titleText2;
    }

    public void setTitleText2(String titleText2)
    {
        this.titleText2 = titleText2;
        invalidate();
    }
}
