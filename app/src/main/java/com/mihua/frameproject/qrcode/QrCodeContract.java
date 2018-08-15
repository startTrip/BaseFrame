package com.mihua.frameproject.qrcode;

import android.graphics.Bitmap;

import com.mihua.code.base.mvp.BasePresenter;
import com.mihua.code.base.mvp.BaseView;
import com.tbruyelle.rxpermissions.RxPermissions;


/**
 * Project: MihuaDemo
 * Author: wm
 * Data:   2017/2/7
 */
public interface QrCodeContract {

    interface QrCodePresenter extends BasePresenter<QrCodeView> {

        void showQr_Code(String content);

        void CheckCamaraPermission(RxPermissions rxPermissions);
    }

    interface QrCodeView extends BaseView {

        void notifyUser();

        void showQrCode(Bitmap bitmap);

        void NavigationToCapture();
    }
}
