package com.mihua.code.base.mvp;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/1/11
 */
public interface BasePresenter<T extends BaseView>{

    void attach(T view);

    void detachView();

}
