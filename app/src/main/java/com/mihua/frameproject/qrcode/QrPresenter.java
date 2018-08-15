package com.mihua.frameproject.qrcode;

import android.Manifest;
import android.graphics.Bitmap;

import com.mihua.code.base.mvp.RxPresenter;
import com.mihua.thirdplatform.zxing.activity.ZXingUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Project: MihuaDemo
 * Author: wm
 * Data:   2017/2/7
 */
public class QrPresenter extends RxPresenter<QrCodeContract.QrCodeView> implements QrCodeContract.QrCodePresenter {


    @Override
    public void showQr_Code(String content) {
        if (content == null) {

            mView.notifyUser();

        }else {

            Bitmap bitmap = ZXingUtils.createImage(content, 400, 400, null);
            mView.showQrCode(bitmap);

        }
    }

    @Override
    public void CheckCamaraPermission(RxPermissions rxPermissions) {

        Subscription subscribe = rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {

                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            mView.NavigationToCapture();
                        } else {
                            mView.notifyUser();
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}