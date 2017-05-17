package com.mihua.code.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/1/11
 */
public abstract class BaseActivity <T extends BasePresenter> extends AppCompatActivity implements BaseView {

    public T mPresenter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mPresenter = getPresenter();

        if (mPresenter != null){
            mPresenter.attach(this);
        }
        // 初始化事件和数据
        initEventAndData(savedInstanceState);
        // 设置监听器
        setListener();
    }

    // 得到布局文件
    protected abstract int getLayout();

    // 得到 presenter 对象
    protected abstract T getPresenter();

    // 初始化数据
    protected abstract void initEventAndData(Bundle bundle);

    // 初始化监听器
    protected abstract void setListener();




    // 显示进度Dialog
    @Override
    public void showProgressDialog() {

    }

    // 隐藏进度的 Dialog
    @Override
    public void hideProgressDialog() {

    }



    @Override
    protected void onDestroy() {
        // 绑定生命周期
        if (mPresenter != null)
            mPresenter.detachView();
        mUnBinder.unbind();
        super.onDestroy();
    }

}
