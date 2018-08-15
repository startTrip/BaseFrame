package com.mihua.frameproject.vlayout.home;

import com.mihua.code.base.mvp.BasePresenter;
import com.mihua.code.base.mvp.BaseView;
import com.mihua.frameproject.vlayout.bean.SlideBean;

import java.util.ArrayList;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/3/31
 */
public interface HomeContract {

    interface View extends BaseView{

        void hasBanner(boolean b);

        void setBannerData(ArrayList<SlideBean> list);

//        void hasClassify(boolean b);
//
//        void setClassifyData(List<HomeClassifyTitle> homeClassifyTitles);

        void notifyData();
    }

    interface Presenter extends BasePresenter<View>{

        void getHomeData();
    }

}
