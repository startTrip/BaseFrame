package com.mihua.code.update;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;

import com.mihua.code.http.BaseApi;
import com.mihua.code.http.retrofit.RetrofitManger;
import com.mihua.code.utils.AppUtils;
import com.mihua.code.widget.dialog.dialog.BottomDialog;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class UpdateManage {

    private Context mContext;
    private String mUrl;
    private byte[] mPostData;
    private boolean mIsManual;
    private boolean mIsWifiOnly;
    private int mNotifyId = 0;

    private UpdateManage(Builder builder){
        mContext = builder.mContext;
        mUrl = builder.mUrl;
        mPostData = builder.mPostData;
        mIsManual = builder.mIsManual;
        mIsWifiOnly= builder.mIsWifiOnly;
        mNotifyId = builder.mNotifyId;
    }

    public static void checkVersion(final FragmentActivity context){

        Subscription subscribe = RetrofitManger.getInstance().getApiService(
                "http://shop.trqq.com/mobile/", BaseApi.class).get("index.php?act=index&op=apk_version",UpdateInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UpdateInfo>() {
                    @Override
                    public void call(UpdateInfo updateInfo) {
                        if (updateInfo != null) {
                            String version = updateInfo.getDatas().getVersion();
                            String appVersionName = AppUtils.getAppVersionName(context);
                            if(1<Integer.parseInt(version)){
                                BottomDialog.create(context.getSupportFragmentManager())
                                        .setViewListener(new BottomDialog.ViewListener() {
                                            @Override
                                            public void bindView(View v) {

                                            }
                                        })
                                        .setGravity(Gravity.CENTER)
                                        .setCancelOutside(true)
                                        .setDimAmount(0.4f)
                                        .show();
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    public static class Builder{

        private Context mContext;
        private String mUrl;
        private byte[] mPostData;
        private boolean mIsManual;
        private boolean mIsWifiOnly;
        private int mNotifyId = 0;

        public Builder Builder(Context context){
            mContext = context;
            return this;
        }

        public Builder setUrl(String url){
            mUrl = url;
            return this;
        }

        public void setPostData(byte[] postData) {
            mPostData = postData;
        }

        public void setManual(boolean manual) {
            mIsManual = manual;
        }

        public void setWifiOnly(boolean wifiOnly) {
            mIsWifiOnly = wifiOnly;
        }

        public void setNotifyId(int notifyId) {
            mNotifyId = notifyId;
        }

        public UpdateManage build(){
            return new UpdateManage(this);
        }
    }
}
