package com.mihua.code.base.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/3/30
 */
public class RxPresenter <T extends BaseView> implements BasePresenter<T> {


    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    // 取消订阅
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
    // 添加 到 subScribe group 中
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attach(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }
}
