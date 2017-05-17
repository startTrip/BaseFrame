package com.mihua.frameproject.vlayout.home;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/1
 */
public class TitleBehavior extends CoordinatorLayout.Behavior<View> {

    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    // 当向下的时候， dyConsumed > 0   dyUnconsumed = 0
    // 当向上的时候， dyConsumed < 0   dyUnconsumed = 0
    // 当向上到顶的时候， dyConsumed = 0   dyUnconsumed < 0
    // 当向下到底的时候， dyConsumed = 0   dyUnconsumed > 0
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        Log.d("AAAAA","dxConsumed"+dxConsumed+",dyConsumed"+dyConsumed+",dxUnConsumed"+dxUnconsumed+",dyUnConsumed"+dyUnconsumed);
        if(dyConsumed>0){
            int scrollY = target.getScrollY();
            float a = scrollY/800.0f;
            child.setAlpha(a>1?1:a);
        }
        if(dyConsumed < 0||dyUnconsumed<0){
            int scrollY = target.getScrollY();
            Log.d("AAAAA","scrollY="+scrollY);
            if(scrollY < 800){
                float b = scrollY/800.0f;
                child.setAlpha(b<0?0:b);
            }
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {

        Log.d("CCCC","velocityY"+velocityY+",consumed"+consumed);
        return true;
    }
}
