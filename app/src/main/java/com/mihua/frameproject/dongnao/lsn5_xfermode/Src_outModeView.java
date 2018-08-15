package com.mihua.frameproject.dongnao.lsn5_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mihua.frameproject.R;
import com.orhanobut.logger.Logger;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/15
 *     desc   : 使用 Xfermode 用于演示橡皮擦效果
 * </pre>
 */
public class Src_outModeView extends View {

    private Bitmap mSrc;
    private Path mPath;
    private Paint mPaint;
    private Bitmap mDst;
    private float mStartX;
    private float mStartY;

    public Src_outModeView(Context context) {
        this(context,null);
    }

    public Src_outModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.a1);
        mDst = Bitmap.createBitmap(mSrc.getWidth(), mSrc.getHeight(), Bitmap.Config.ARGB_8888);
        Logger.d("图片"+mSrc.getWidth()+","+mSrc.getHeight());
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layoutId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // 先画手势的区域
        Canvas c = new Canvas(mDst);
        c.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
        c.drawPath(mPath,mPaint);
        canvas.drawBitmap(mDst,0,0,mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        canvas.drawBitmap(mSrc,0,0,mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layoutId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                Logger.d("down"+ mStartX +","+ mStartY);
                mPath.moveTo(mStartX, mStartY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mStartX+event.getX())/2;
                float endY = (mStartY+event.getY())/2;
                Logger.d("down"+endX+","+endY);
                mPath.quadTo(mStartX, mStartY,endX,endY);
                mStartX = endX;
                mStartY =endY;
                break;
        }
        postInvalidate();
        return true;
    }
}
