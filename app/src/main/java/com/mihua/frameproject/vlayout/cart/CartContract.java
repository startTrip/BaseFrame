package com.mihua.frameproject.vlayout.cart;

import android.content.Context;

import com.mihua.code.base.mvp.BasePresenter;
import com.mihua.code.base.mvp.BaseView;
import com.mihua.frameproject.vlayout.cart.bean.ShoppingCartBean;

import java.util.List;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/13
 */
public interface CartContract {

    interface View extends BaseView{

        void setCartData(List<ShoppingCartBean> list);

    }

    interface Presenter extends BasePresenter<View>{

        void getCartData(Context context);

    }
}
