package com.mihua.frameproject.dongnao.lsn4_paint_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.mihua.frameproject.R;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/14
 *     desc   : Shader 着色器   圆形图片用于演示效果
 *              canvas 画形状 ，shader用于着色
 *              bitmapshader 用于贴图
 * </pre>
 */
public class CircleBitmapShader extends View{


    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingBottom;
    private int mPaddingTop;
    private BitmapShader mBitmapShader;

    public CircleBitmapShader(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cpt1);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mWidth = bitmap.getWidth();
        mHeight =  bitmap.getHeight();

        // 得到  padding
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();
        mPaddingTop = getPaddingTop();

        Matrix matrix = new Matrix();
        matrix.setTranslate(mPaddingLeft,mPaddingTop);
        mBitmapShader.setLocalMatrix(matrix);


        mRadius = Math.min(mWidth,mHeight);
        mPaint = new Paint();
        mPaint.setShader(mBitmapShader);
    }

    public CircleBitmapShader(Context context) {
        this(context,null);
    }

    /**
     *  canvas 指的是测量完成以后 那个设置的区域的 大小，
     *          画中的x , y  都是在画布里面 以左上角为原点的相对距离
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用  shapeDrawable 定义一个圆形的shape ,  然后设置大小
        // 使用 bitmapShader 去定义圆形图片的外观
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setBounds(mPaddingLeft,mPaddingTop,mPaddingLeft+mWidth,mPaddingTop+mHeight);
        Paint paint = shapeDrawable.getPaint();
        paint.setShader(mBitmapShader);
//        shapeDrawable.draw(canvas);


        int[] colors = new int[]{Color.RED,Color.GREEN,Color.YELLOW};
        float[] positions = new float[]{0,1,2};
        LinearGradient linearGradient = new LinearGradient(0,0,mPaddingLeft+mWidth,mPaddingTop+mHeight,colors,positions, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);

        ComposeShader composeShader = new ComposeShader(mBitmapShader,linearGradient, PorterDuff.Mode.MULTIPLY);
        paint.setShader(composeShader);
        shapeDrawable.draw(canvas);
////        // 计算 圆心坐标
//        int x = (mPaddingLeft+mPaddingRight+mRadius)/2;
//        int y = (mPaddingTop+mPaddingBottom+mRadius)/2;
//
//        canvas.drawCircle(x,y,mRadius/2,mPaint);
    }

    /**
     *
     * @param widthMeasureSpec   父布局通过 测量自己以后 得到的 widthMeasureSpec
     * @param heightMeasureSpec   父布局通过测量自己以后得到的 heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 宽度和高度  0      系统建议的高度一般为0
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        // 如果自己的大小固定的话    mode 就是为 Exactly（确定的）
        // 如果自己的大小是match_parent 取决于父亲的大小，如果父布局的大小也为match_parent
            // 的话 则自己的模式 就为  Exactly，父布局不确定的话自己也就不确定为 At_Most
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        //   1080
        // 只要自己的大小不确定的话那么 size 就为父布局的大小
        int size = MeasureSpec.getSize(widthMeasureSpec);

        boolean w = mode==MeasureSpec.AT_MOST;
        boolean w1 = mode==MeasureSpec.EXACTLY;

        // 0
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int mode1 = MeasureSpec.getMode(heightMeasureSpec);
        int size1 = MeasureSpec.getSize(heightMeasureSpec);

        boolean h = mode==MeasureSpec.AT_MOST;
        boolean h1 = mode==MeasureSpec.EXACTLY;
        //
        int defaultSizeWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec);

        int defaultSizeHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        setMeasuredDimension(mWidth+paddingLeft+paddingRight,mHeight+paddingTop+paddingBottom);
    }
}
