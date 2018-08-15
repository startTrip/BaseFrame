package com.mihua.frameproject.greendao;

import android.os.Bundle;

import com.mihua.code.base.mvp.BaseActivity;
import com.mihua.frameproject.R;

public class GreenDaoActivity extends BaseActivity<GreenDaoContact.Presenter> implements GreenDaoContact.View {


    @Override
    protected int getLayout() {
        return R.layout.activity_green_dao;
    }

    @Override
    protected GreenDaoContact.Presenter getPresenter() {
        return new GreenDaoPresenter();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    @Override
    protected void setListener() {

    }
}
