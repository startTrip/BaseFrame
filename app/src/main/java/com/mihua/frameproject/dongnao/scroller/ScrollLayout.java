package com.mihua.frameproject.dongnao.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/21
 *     desc   :
 * </pre>
 */
public class ScrollLayout extends FrameLayout {

    private Scroller mScroller;

    public ScrollLayout(Context context) {
        this(context,null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    // 该函数会在View 重绘的时候被重用
    @Override
    public void computeScroll() {
        super.computeScroll();
        // 返回 true 表示滚动尚未完成
        if(mScroller.computeScrollOffset()){
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            Logger.d("distance","x="+currX+",y"+currY);
            this.scrollTo(currX, currY);
            // 请求重新绘制这个View,从而又会导致computeScroll被调用，然后继续滚动
            this.postInvalidate();
        }
    }

    public void scrollTo(int y){

        int scrollX = getScrollX();
        int scrollY = getScrollY();
        Logger.d("distance111111","x="+scrollX+",y"+scrollY);
        mScroller.startScroll(scrollX, scrollY,0,y);
        this.invalidate();

    }
}
