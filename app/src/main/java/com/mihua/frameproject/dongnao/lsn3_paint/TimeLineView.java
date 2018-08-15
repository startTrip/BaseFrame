package com.mihua.frameproject.dongnao.lsn3_paint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.mihua.frameproject.R;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/09
 *     desc   :
 * </pre>
 */
public class TimeLineView extends View {


    private Drawable mMarkerDrawable;
    private float mMarkerSize;
    private int mLineSize;
    private Paint mPaint;
    private int mLineColor;
    private int mMarkerDrawableWidth;
    private int mMarkerDrawableHeight;
    private Rect mSrcRect;
    private Rect mDesRect;

    public TimeLineView(Context context) {
        this(context,null);
    }

    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //  先得到自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TimeLineView);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_MarkerSize, 10);
        mMarkerDrawable = typedArray.getDrawable(R.styleable.TimeLineView_Marker);
        mLineSize = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_LineSize, 1);
        mLineColor = typedArray.getColor(R.styleable.TimeLineView_LineColor, Color.RED);
        typedArray.recycle();

        mMarkerDrawableWidth = mMarkerDrawable.getIntrinsicWidth();
        mMarkerDrawableHeight = mMarkerDrawable.getIntrinsicHeight();

        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineSize);

        mSrcRect = new Rect();
        mDesRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 先得到宽和高
        int width = getWidth();
        int height = getHeight();
        int top = getTop();
        int bottom = getBottom();
        int right = getRight();
        int left = getLeft();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        mDesRect.top = (bottom-top)/2-width/2;
        mDesRect.bottom = (bottom-top)/2+width/2;
        mDesRect.left = left+paddingLeft;
        mDesRect.right = right-paddingRight;

        mMarkerDrawable.setBounds(mDesRect);
        mMarkerDrawable.draw(canvas);

        canvas.drawLine(width/2,top,width/2,mDesRect.top,mPaint);
        canvas.drawLine(width/2,mDesRect.bottom,width/2,bottom,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = measureWidth(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        // 先得到父布局的宽度模式和高度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        switch (widthMode){
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                int desSize = (int) Math.max(mMarkerSize,mMarkerDrawableWidth+paddingLeft+paddingRight);
                result = widthSize<desSize?widthSize:desSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        return result;
    }
}
