package com.mihua.code.base.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/1/12
 */

/**
 *
 * @param <T>  对应的 Presenter 继承 BasePresenter
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected T mPresenter;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;
    protected Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(getFragmentLayout(), container, false);

        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        mUnBinder = ButterKnife.bind(this, view);

        initEventAndData(savedInstanceState);

        setListener();
    }


    // 得到　Fragment　的布局文件

    protected abstract int getFragmentLayout();

    // 得到 presenter 对象
    protected abstract T getPresenter();

    // 初始化数据
    protected abstract void initEventAndData(Bundle s);

    // 初始化监听器
    protected abstract void setListener();



    @Override
    public void showProgressDialog() {

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("正在加载.....");
        mProgressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
