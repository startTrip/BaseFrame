package com.mihua.frameproject.dongnao.lsn7_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mihua.frameproject.R;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/19
 *     desc   :
 * </pre>
 */
public class CanvasView extends View {


    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a1);
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas 画的东西都是 canvas 左上角为原点的坐标
        // gettop 是得到左边的px值;
        int left = getPaddingLeft();
        int top = getPaddingTop();
        canvas.drawBitmap(mBitmap, left, top,mPaint);

        canvas.save();
        canvas.rotate(90);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        canvas.drawText("漫画",left+50,-top-50-(mPaint.getFontMetrics().descent-mPaint.getFontMetrics().ascent),mPaint);
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth = measureWidth(widthMode, widthSize);
        int measuredHeight = measureHeight(heightMode, heightSize);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureHeight(int heightMode, int heightSize) {
        int height = 0;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                height = mHeight+paddingTop+paddingBottom;
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
        }
        return height;
    }

    private int measureWidth(int widthMode, int widthSize) {
        int width = 0;

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                case MeasureSpec.UNSPECIFIED:
                    width = mWidth + paddingLeft + paddingRight;
                    break;
                case MeasureSpec.EXACTLY:
                    width = widthSize;
                    break;
        }
        return width;
    }
}
