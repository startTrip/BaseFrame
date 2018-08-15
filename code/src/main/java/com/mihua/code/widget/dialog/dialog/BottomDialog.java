package com.mihua.code.widget.dialog.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/04
 *     desc   :
 * </pre>
 */
public class BottomDialog extends BaseBottomDialog{

    private static final String KEY_LAYOUT_RES = "layout_res";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_DIM = "dim";
    private static final String KEY_CANCEL_OUTSIDE = "cancel_outside";
    public static final String KEY_GRAVITY = "gravity";

    private FragmentManager mFragmentManager;

    private boolean mIsCancelOutside = super.getCancelOutside();

    private String mTag = super.getFragmentTag();

    private float mDimAmount = super.getDimAmount();

    private int mHeight = super.getHeight();

    private int mGravity = super.getLayoutGravity();

    @LayoutRes
    private int mLayoutRes;

    private ViewListener mViewListener;

    public static BottomDialog create(FragmentManager manager) {
        BottomDialog dialog = new BottomDialog();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
            mGravity = savedInstanceState.getInt(KEY_GRAVITY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        outState.putInt(KEY_GRAVITY,mGravity);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public BottomDialog setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public BottomDialog setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public BottomDialog setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public BottomDialog setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public BottomDialog setTag(String tag) {
        mTag = tag;
        return this;
    }

    /**
     * @param dim  亮度
     * @return
     */
    public BottomDialog setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public BottomDialog setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    /**
     * 设置显示的位置，目前显示中间位置和底部
     * @param gravity Gravity.bottom  和  Gravity.Center
     * @return
     */
    public BottomDialog setGravity(int gravity){
        mGravity = gravity;
        return  this;
    }

    public int getLayoutGravity(){
        return mGravity;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public boolean getCancelOutside() {
        return mIsCancelOutside;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }

    public interface ViewListener {
        void bindView(View v);
    }

    public BaseBottomDialog show() {
        show(mFragmentManager);
        return this;
    }
}
