package com.mihua.code.base.mvp;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/1/12
 */

//  PresenterIml 的实现 用于绑定 View 用于 presenter 与 Activity 交互
public class BasePresenterIml<T extends BaseView> implements BasePresenter<T>{

    protected T mView;

    @Override
    public void attach(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
