package com.mihua.frameproject.request;

import com.mihua.code.base.mvp.BasePresenter;
import com.mihua.code.base.mvp.BaseView;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/10
 */
public interface RequestContact {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{

        void showGet();

        void showPost();

        void showJsonUpload();

        void showImgUpload();

        void showFileUpload();

    }

}
