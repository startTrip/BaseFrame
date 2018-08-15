package com.mihua.frameproject.dongnao.lsn3_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/05
 *     desc   : 圆形进度指示器  用于使用 Paint 画图一些api
 *              1、paint 画图形
 *              2、paint 画文字
 * </pre>
 */
public class CircleProgressBar extends View {


    private Paint mPaint;
    private float progress = 0;

    public CircleProgressBar(Context context) {
        this(context,null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    // 初始化
    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 先得到 自身控件宽度，只有在 layout 以后才会有值
        int width = getWidth();
        int height = getHeight();

        int centerWidth = width/2;
        int centerHeight = height/2;

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);
        // 先画圆环
        canvas.drawCircle(centerWidth,centerHeight,80,mPaint);

        // 然后画文字  进度百分比
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(0);
        mPaint.setTextSize(20);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        int percent = (int)(progress /100 *100);
        String strPercent = percent+"%";
        if (percent!=0){
            // 得到画字体的   Metrics
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

            // 画文字是画文字的 基线     baseline = center - (fm.bottom+fm.top)/2
            canvas.drawText(strPercent,getWidth()/2-mPaint.measureText(strPercent)/2,
                    getHeight()/2+ (fontMetrics.bottom - fontMetrics.top)/2 -fontMetrics.bottom,mPaint
                    );
        }

        // 画圆弧
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);
        canvas.drawArc(new RectF(width/2-80,height/2-80,width/2+80,height/2+80),
                        0,360*progress/100,false,mPaint);

    }

    public void setProgress(float progress){
        if(progress<0){
            throw new IllegalArgumentException("进度不能小于0");
        }
        this.progress = progress;
    }
}
