package com.mihua.frameproject.request;

import android.os.Bundle;
import android.view.View;

import com.mihua.code.base.mvp.BaseActivity;
import com.mihua.frameproject.R;

import butterknife.OnClick;

public class RequestActivity extends BaseActivity<RequestContact.Presenter> implements RequestContact.View{


    @Override
    protected int getLayout() {
        return R.layout.activity_request;
    }

    @Override
    protected RequestContact.Presenter getPresenter() {
        return new RequestPresenter();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_img_upload, R.id.btn_file_upload,R.id.btn_get, R.id.btn_post,R.id.btn_json})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_get:
                mPresenter.showGet();
                break;
            case R.id.btn_post:
                mPresenter.showPost();
                break;

            case R.id.btn_json:
                mPresenter.showJsonUpload();
                break;
            case R.id.btn_img_upload:
                mPresenter.showImgUpload();
                break;
            case R.id.btn_file_upload:
                mPresenter.showFileUpload();
                break;
        }
    }
}
